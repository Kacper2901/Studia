let suma_kw a b c = 
  let min_val = min a (min b c) in
  a*a +b*b + c*c - min_val*min_val;;

  

  let wynik = suma_kw 5 5 7;;
  print_int (wynik);;
  print_newline ();;