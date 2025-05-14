type bop =
  (* arithmetic *)
  | Add | Sub | Mult | Div
  (* logic *)
  | And | Or
  (* comparison *)
  | Eq | Leq




type ident = string

type expr = 
  | Int   of int
  | Binop of bop * expr * expr
  | Bool  of bool
  | If    of expr * expr * expr
  | Let   of ident * expr * expr
  | Var   of ident
  (* |For of ident* expr * expr * expr
  |Integral of ident*expr * expr *expr trzeba otypowac tak jak w eval_op zeby expr nie dostal bool *)
  |Unit