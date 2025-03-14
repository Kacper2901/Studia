(*    (a,b)  (e,f)
      (c,d)  (g,h)    *)

      let matrix_mult (a,b,c,d) (e, f, g, h) = 
        (a*e + b*g, a*f + b*h, c*e + d*g, c*f + d*h)
      
      let matrix_id = (1, 0, 0, 1)
      
      let rec matrix_fast m k =
          if k = 0 then matrix_id
          else if k mod 2 = 1 then matrix_mult m (matrix_fast m (k-1))
          else let half = matrix_fast m (k/2) in
          matrix_mult half half
          
      
      let fib_matrix k =
        let(_, fk, _,_) = matrix_fast (1, 1, 1, 0) k in fk
      
      let() = 
      print_int(fib_matrix(5))


      let rec matrix_expt m k =
        match k with
        | 0 -> matrix_id  (* Macierz jednostkowa dla k=0 *)
        | 1 -> m          (* Zwrot samej macierzy, gdy k=1 *)
        | _ when k mod 2 = 0 -> 
            let half = matrix_expt m (k / 2) in
            matrix_mult half half  (* k jest parzyste, więc podnosimy połowę do kwadratu *)
        | _ -> 
            matrix_mult m (matrix_expt m (k - 1))  (* k jest nieparzyste, więc mnożymy przez m *)