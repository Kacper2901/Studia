
(* The type of tokens. *)

type token = 
  | UNIT
  | TIMES
  | THEN
  | RPAREN
  | PLUS
  | OR
  | MINUS
  | LPAREN
  | LET
  | LEQ
  | INT of (int)
  | IN
  | IF
  | IDENT of (string)
  | EQ
  | EOF
  | ELSE
  | DIV
  | BOOL of (bool)
  | AND

(* This exception is raised by the monolithic API functions. *)

exception Error

(* The monolithic API. *)

val main: (Lexing.lexbuf -> token) -> Lexing.lexbuf -> (Ast.expr)
