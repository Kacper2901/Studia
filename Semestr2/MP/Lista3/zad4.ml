let empty_set = fun x -> false;;
let singleton a = fun x -> x==a;;
let in_set a s = s a;;
let union s t = fun x -> s x || t x;;
let intersect a b = fun x -> a x && b x;;


