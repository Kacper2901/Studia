let rec factorial a wyn= 
        if a == 0 then wyn
        else factorial (a-1)(wyn*a)


let factorial2 a = 
   let rec aux a wyn =  
       if a = 0 then wyn
       else aux (a-1) wyn*a
   in aux a 1


let rec append a x =
    match a with
    |[] -> [x]
    |h :: hs -> h::(append hs x)


let rec pop a =
    match a with
    |[x] -> []
    |[] -> []
    |x :: xs -> x:: (pop xs)
 
   
let  reverb a =
   let rec aqq a n =
    match a with
    |[] -> n
    |x::xs -> aqq xs (x::n)
   in aqq a []

let rec insertion arr n  = 
        match arr with
        |[] -> [n]
        |x::xs -> if n<x then n::(x::xs) else x::(insertion xs n)

let rec sort arr = 
    match arr with
    |[] -> []
    |x::xs -> insertion (sort xs) x

let rec map arr f = 
    match arr with
    |[] -> []
    |x::xs ->(f x)::(map xs f)


type 'a tree =
   | Empty
   | Node of 'a * 'a tree * 'a tree


let tree = Node(1,Node(2,Empty,Empty),Empty)

let rec dfs tree exec = 
        match tree with
        |Empty -> [] 
        |Node(v,l,p) -> (dfs l exec) @ (dfs p exec) @[v]
