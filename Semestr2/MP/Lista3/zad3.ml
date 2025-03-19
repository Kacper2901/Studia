let build_list n f = (*bierzemy dlugossc listy i funkcje*)
  let rec it f xs a =   (*iteracja *)
    if a >= 0 then  
      it f ((f a) :: xs) (a-1) (*robimy kolejna iteracje ale tym razem lista ma dodatkowy element f(a) na poczatku listy*)
    else 
      xs (*xs jet tablica*)
  in it f [] (n-1);; (*wywolujemy iteracje dla funckji f pustej listy i n-1[bo bedzie to ostatni element]*)

let negatives n = build_list n (fun x -> -(x+1));;  

let reciprocals n = build_list n (fun x -> 1. /.  float_of_int((x+1)));;

let evens n = build_list n (fun x -> 2*x);;

let identityM n = build_list n (fun i -> build_list n (fun j -> if i = j then 1 else 0));;

(*budujemy liste n elementow, dla kazdego stosujemy funkcej w nawiasie*)
(*gdy a>=0 to stosujemy funkcje w nawiasie do kazdego a>=0 gdzie a jest aplikowane jako i z pierwotenj funkcji*)
(*funckja bierze to i [czyli a] i buduje liste n elementowa z funkcja w nawiasie*)
(*znowu w build_list wchodzimy w sytacje ze dla j=a dodajemy do tablicy 0 jesli j != i, czyli kolumna rowna sie weirszowi*)


negatives 5;;
reciprocals 5;;
evens 5;;
identityM 5;;