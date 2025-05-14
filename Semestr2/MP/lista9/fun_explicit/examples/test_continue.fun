 interp "let x = ref 0 in
  let sum = ref 0 in
  let _ = while !x <= 5 do
    if !x = 2 then
      let _ = x := !x + 1 in
      let _ = continue in
      ()
    else
      let _ = sum := !sum + !x in
      let _ = x := !x + 1 in
      ()
  in
  !sum
";;
