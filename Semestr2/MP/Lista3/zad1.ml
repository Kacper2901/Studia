let fold_left f a xs =        (*dajemy funkcje, wartosc od jakiej zaczynamy, liste*)
  let rec it xs acc =         (*iteracyjnie dajemy liste i akumulator*)
    match xs with             (*dopasowujemy liste*)
    |[]->acc                  (*gdy pusta zwracamy akumulator [czyli w naszym przypadku mnozymy przez1]*)
    |h::t -> it t (f acc h)   (*rekurencyjnie tail listy a akumulatorem staje sie f(akumulator, head)*)
  in it xs a;;                (*wywolanie rekurencyjnej iteracji*)

let product xs = if xs = [] then 0 else fold_left ( * ) 1 xs;;

product [1;2;3;4;5]


(*fold_left dziala tak jak nawiasowanie koniunkcji,czyli mnozymy dwa pierwsze, potem wynik z kolejnym itd...*)