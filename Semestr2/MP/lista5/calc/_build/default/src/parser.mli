
(* The type of tokens. *)

type token = 
  | TIMES
  | RPAREN
  | POW
  | PLUS
  | MINUS
  | LPAREN
  | LOG
  | FLOAT of (float)
  | E_CONST
  | EOF
  | DIV

(* This exception is raised by the monolithic API functions. *)

exception Error

(* The monolithic API. *)

val main: (Lexing.lexbuf -> token) -> Lexing.lexbuf -> (Ast.expr)
