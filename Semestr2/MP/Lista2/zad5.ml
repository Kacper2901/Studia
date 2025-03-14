let rec maximum xs =
  match xs with
  | [] -> neg_infinity
  | [x] -> x
  | y :: ys -> max y (maximum ys)




  let lista1 = [2.;3.6;4.7;5.2]
  let lista2 = []

  let()= 
  print_string(string_of_float(maximum lista1));
  print_newline ()


  let()= 
  print_string(string_of_float(maximum lista1));
  print_newline ()

  let()= 
  print_string(string_of_float(maximum lista2));
  print_newline ()