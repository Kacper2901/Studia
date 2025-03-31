let parens_ok str = 
  let list_of_string = String.to_seq str |> List.of_seq in 
  let rec f arr balance = 
    match arr with
    |[] -> if balance = [] then true else false
    |h::t -> if h = '(' || h = '{' || h = '[' then f t (h::balance) 
        else match balance with
          |[] -> false
          |x::xs -> match x with 
            |'(' -> if h = ')' then f t xs else false
            |'[' -> if h = ']' then f t xs else false 
            |'{' -> if h = '}' then f t xs else false
            | _ -> false

                                                          
  in f list_of_string []