module type KDICT = sig
  type key (*mowimy ze key bedzie mialo okreslony typ, ale szczegoly podajemy potem w module*)
  type 'a dict (*dict moze przechowywac wartosc dowolnego typu *)
  
  val empty : 'a dict
  val insert : key -> 'a -> 'a dict -> 'a dict
  val remove : key -> 'a dict -> 'a dict
  val find_opt : key -> 'a dict -> 'a option
  val find : key -> 'a dict -> 'a
  val to_list : 'a dict -> (key * 'a) list
end


(*musielismy miec pewnosc ze key bedzie porownywalny, aby mozna bylo zastosowac map na slowniku*)