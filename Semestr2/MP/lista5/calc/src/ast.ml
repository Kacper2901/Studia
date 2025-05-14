type bop = Add | Sub | Mult | Div | Pow (*zad6*)

type pref = Log (*zad6*)

type expr = 
    | Float of float (*float*)
    | Binop of bop * expr * expr
    | Pre of pref * expr (**zad6*)
    (* | E of float zad6 *)
