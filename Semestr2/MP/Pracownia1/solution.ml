let alfa = 0.75

type 'a tree = Leaf | Node of 'a tree * 'a * 'a tree
type 'a sgtree = { tree : 'a tree; size : int; max_size: int }
 


let alpha_height (n : int) : int =
    int_of_float(floor(log(float_of_int(n))/.log(1./. alfa)))


let empty : 'a sgtree =
    {tree = Leaf; size = 0; max_size = 0} 


let find (x : 'a) (sgt : 'a sgtree) : bool =
    let rec find_rec x tree = 
        match tree with
        |Leaf->false
        |Node(l,v,p)->
              if v=x then
                true
              else          
                if x < v then
                  find_rec x l
                else find_rec x p
    in find_rec x sgt.tree


let rec size_of_tree (tree: 'a tree): int = 
    match tree with
    |Leaf -> 0
    |Node(l,v,p)-> size_of_tree l + 1 + size_of_tree p


let is_balanced (tree: 'a tree):bool = 
    match tree with
    |Leaf -> true
    |Node(l,v,p) ->
          let left_subtree = size_of_tree l in
          let right_subtree = size_of_tree p in
          let whole_tree = left_subtree+right_subtree + 1 in
          float_of_int(left_subtree) < alfa *. float_of_int(whole_tree) &&
          float_of_int(right_subtree) < alfa *. float_of_int(whole_tree) 


let to_list (tree: 'a tree): 'a list =
    let rec to_list_rec tree acc =
      match tree with
      |Leaf -> acc
      |Node (l, v, p) -> to_list_rec l (v::to_list_rec p acc)
    in to_list_rec tree []

      
let build_tree_from_list (lst: 'a list): 'a tree = 
    let rec builder n elements = 
        match n with
        |0 -> (Leaf, elements)
        |_ -> let left = (n-1)/2 in
              let right = n - 1 - left in 
              let (left_tree, rest) = builder left elements in
              match rest with
              |[] -> (left_tree,[])
              |h::t -> let (right_tree, rest_r) = builder right t in 
              (Node(left_tree, h, right_tree),rest_r)
    in
     let (tree, acc) = builder (List.length lst) lst
     in tree


let rec insert_node (x : 'a) (tree : 'a tree) : 'a tree =
    match tree with
    |Leaf -> Node(Leaf, x, Leaf)
    |Node(l, v, p) ->
        if x < v then
          Node(insert_node x l, v, p)
        else
          if x > v then 
            Node(l, v, insert_node x p)
          else 
            tree


let rebuild_balanced (t : 'a tree) : 'a tree =
    let tree = to_list t in build_tree_from_list tree


let change_node (tree: 'a tree)( new_tree: 'a tree)(old: 'a): 'a tree = 
    let rec change_rec t = 
        match t with
        |Leaf -> Leaf
        |Node(l,v,p) -> 
          if old<v then
            Node(change_rec l, v,p)
          else 
            if old>v then 
              Node(l,v, change_rec p)
            else new_tree             
    in change_rec tree


let find_path (x: 'a) (tree: 'a tree): 'a tree list =
    let rec find_path_rec node acc =
      match node with
      |Leaf -> acc
      |Node(l, v, p) ->
          if x = v then Node(l,v,p) :: acc
          else if x < v then find_path_rec l (Node(l,v,p) :: acc)
          else find_path_rec p (Node(l,v,p) :: acc)
    in find_path_rec tree []


let find_scapegoat_and_fix (path: 'a tree list) (tree: 'a tree): 'a tree =
    let rec find_scapegoat_rec arr=
      match arr with
      |[] -> failwith "nie znaleziono kozla"
      |head::tail -> if is_balanced head then find_scapegoat_rec tail else head
    in 
    let scapegoat = find_scapegoat_rec path in
    let rebuilt_scapegoat = rebuild_balanced scapegoat in
    let value_to_replace = match scapegoat with 
                          |Node(_,v,_)-> v
                          |_ -> failwith "brak kozla"
    in
      change_node tree rebuilt_scapegoat value_to_replace


let insert (x : 'a) (sgt : 'a sgtree) : 'a sgtree =
    if find x sgt then failwith "dodawana wartosc juz istnieje w drzewie"
    else 
      let new_tree =  insert_node x sgt.tree in
      let path = find_path x new_tree in
      let depth = List.length path-1 in
      let new_size = sgt.size + 1 in
      let alfa_size = alpha_height new_size in
      let new_max_size = max new_size sgt.max_size in
      let newest_tree = 
        if depth > alfa_size then
          find_scapegoat_and_fix path new_tree
        else
          new_tree
      in {tree = newest_tree; size = new_size; max_size = new_max_size}


let find_min (tree: 'a tree): 'a = 
    let rec min_rec t= 
        match t with
        |Leaf->failwith "puste drzewo"
        |Node(Leaf,v,_) -> v
        |Node(l,v,_) -> min_rec l
    in min_rec tree 


let rec connect (tree: 'a tree) (new_tree: 'a tree)(value: 'a) =
    match tree with
    |Leaf -> new_tree
    |Node(l,v,p) -> 
          if value<v then
            Node(connect l new_tree value,v,p)
          else if value>v then
            Node(l,v,connect p new_tree value)
          else 
            new_tree


let rec remove_min (tree: 'a tree): 'a tree = 
    match tree with
    |Leaf -> Leaf
    |Node(Leaf,_,p) -> p
    |Node(l,v,p) -> Node(remove_min l, v, p) 


let remove (x : 'a) (sgt : 'a sgtree) : 'a sgtree = 
    if find x sgt = false then failwith "usuwana wartość nie istnieje w drzewie"
    else
      let rec rm_search t  = 
        match t with
        |Leaf -> Leaf
        |Node(l,v,p) ->
              if x<v then
                  Node(rm_search l , v ,p)
              else
                  if x>v then
                      Node(l,v, rm_search p )
                  else
                      match l, p with
                      |Leaf,Leaf -> Leaf
                      |Leaf, _ -> p
                      |_, Leaf -> l
                      |_,_ -> let new_node = find_min p in
                              Node(l,new_node,remove_min p)

      in
      let new_tree = rm_search sgt.tree  in
      let new_size = sgt.size - 1 in

      if float_of_int( new_size) < float_of_int(sgt.max_size) *. 0.75 then
          {tree = rebuild_balanced new_tree; size = new_size; max_size = new_size}
      else
          {tree = new_tree; size = new_size; max_size = sgt.max_size} 




          
          
           
              
            