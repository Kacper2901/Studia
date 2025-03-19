type 'a tree = 
  | Leaf 
  | Node of 'a tree * 'a * 'a tree

let t = 
  Node (
    Node (Leaf, 2, Leaf),
    5,
    Node (
      Node (Leaf, 6, Leaf),
      8,
      Node (Leaf, 9, Leaf)
    )
  )



let rec insert_bst x t =
    match t with
    | Leaf -> Node (Leaf, x, Leaf)
    | Node (l, v, r) ->
        if x < v then Node (insert_bst x l, v, r)
        else if x > v then Node (l, v, insert_bst x r)
        else t  (* x już jest, nie dodajemy duplikatów *)
  