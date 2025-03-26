module type DICT = sig (*szkielet ktorego sie spodziewa*)
  type ('a , 'b ) dict
  val empty : ('a , 'b ) dict
  val insert : 'a -> 'b -> ('a , 'b ) dict -> ('a , 'b ) dict
  val remove : 'a -> ('a , 'b ) dict -> ('a , 'b ) dict
  val find_opt : 'a -> ('a , 'b ) dict -> 'b option
  val find : 'a -> ('a , 'b ) dict -> 'b
  val to_list : ('a , 'b ) dict -> ('a * 'b ) list
  end

  module ListDict : DICT = struct (*"pudelko z kodem"*)
    type ('a, 'b) dict = ('a * 'b) list (*dict bedzie bral dowolne dwa typy i tworzyl z nich krotke w liscie*)
    
    let empty = []
    
    let insert key value dict =
      let rec it key value dict acc =   (*zagniezdzona funkcja widoczna tylko wewnatrz wiec nie wplywa na typ zewnetrznej*)
        match dict with
        | [] -> (key, value) :: acc
        | (k,v)::tail -> if k = key then acc @ [(k,v)] @ tail (*jesli git to laczy to co dotej pory odczepilismy z krotka z tym do czego jeszcze nie dotarlismy*)
        else it key value tail ((k,v)::acc)
      in it key value dict []

    
    let remove key dict =
      let rec it key dict acc = 
        match dict with
        | [] -> acc
        | (k,v)::tail -> if k <> key then it key tail ((k,v)::acc) (*jesli nie pokrywaja sie klucze to idziemy dalej i podczepiamy odczepiony do acc*)
        else acc @ tail (*jesli beda rowne to znalezlismy wiec laczymy odczepione do tej pory z tailem*)
      in it key dict []
    
      let rec find_opt key dict =
        match dict with
        | [] -> None 
        | (k, v) :: tail -> if k = key then Some(v) else find_opt key tail (*gdy zwrocone zostanie None to bedzie zapakowane do Some*)
       
    
    let rec find key dict =
    match dict with 
    | [] -> failwith "Key not found" (*rzucany jest wyjątek wiec typ sie zgadza bo nie istnieje zwracana wartosc*)
    | (k,v)::tail -> if k = key then v
      else find key tail 


    let to_list dict = dict (*dict zostalo zatypowane jako ten typ*)

  end
  