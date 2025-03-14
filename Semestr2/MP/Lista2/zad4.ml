let rec mem x xs =
  match xs with
  | [] -> false
  | y :: ys -> if x = y then true else mem x ys


  let lista1 = [2;3;4;5]
  let lista2 = []

  let()= 
  print_string(string_of_bool(mem 4 lista1));
  print_newline ()


  let()= 
  print_string(string_of_bool(mem 6 lista1));
  print_newline ()


  let()= 
  print_string(string_of_bool(mem 6 lista2))




  