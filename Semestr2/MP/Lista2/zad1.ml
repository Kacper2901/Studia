let rec fib n = 
  if n = 0 then 0
  else if n = 1 then 1 
  else fib (n-1) + fib (n-2)

(* fib_i jest tylko "interfejsem" wywolujacym prawdziwa funkcje iter, zmienne sa niemutowalne wiec korzystamy z rekurencji, aby zrobic cos na wzor iteracji *)
let fib_i n = 
  let rec iter n a b = 
    if n = 0 then a
    else iter (n-1) b (a+b)
  in iter n 0 1


let()=
print_int(fib(10))

let()=
print_int(fib_i(10))


let()=
print_int(fib(40))

let()=
print_int(fib_i(1000))

