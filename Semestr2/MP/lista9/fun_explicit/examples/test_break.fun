  let x = ref 0 in
  let _ = while true do
    if !x = 2 then
      let _ = x := !x + 1 in
      ()
    else
      break
  in
  !x

