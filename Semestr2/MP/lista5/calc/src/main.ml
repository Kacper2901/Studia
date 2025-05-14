open Ast

let parse (s : string) : expr =
    Parser.main Lexer.read (Lexing.from_string s)

let eval_op (op : bop) (v1 : float) (v2 : float) : float =
    match op with
    | Add -> v1 +. v2
    | Sub -> v1 -. v2
    | Mult -> v1 *. v2
    | Div -> v1 /. v2
    | Pow -> Float.pow v1 v2

let eval_pre (pre: pref) (v1: float): float = 
    match pre with
    |Log -> log v1

let rec eval (e : expr) : float =
    match e with
    | Float i -> i
    | Binop (op, e1, e2) -> eval_op op (eval e1) (eval e2)
    | Pre (pre, e1) -> eval_pre pre (eval e1)
    

let interp (s : string) : float =
    eval (parse s)

