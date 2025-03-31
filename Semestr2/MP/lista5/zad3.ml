let parens_ok str = 
  let list_of_string = String.to_seq str |> List.of_seq in 
  let rec f arr balance = 
    match arr with
    |[] -> if balance <> 0 then false else true
    |h::t -> if h<>'(' && h<>')' then false else if balance<0 then false else if h=='(' then f t (balance + 1) else f t (balance - 1)
  in f list_of_string 0