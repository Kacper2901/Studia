let compose f g x = f (g x);; (*bierzemy funkcje f i g oraz wartosc g dla ktorej bedziemy wykonywac, wewnatrz funkcji aplikujemy do f wartosc g(x) *)

let square x = x * x;;

let inc x = x + 1;;

compose square inc 5;;
compose inc square 5;; 

