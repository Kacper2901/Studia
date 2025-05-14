
(* The type of tokens. *)

type token = 
  | WITH
  | UNITQ
  | UNIT
  | TIMES
  | THEN
  | SND
  | RPAREN
  | PLUS
  | PAIRQ
  | OR
  | NUMBERQ
  | MINUS
  | MATCH
  | LPAREN
  | LET
  | LEQ
  | INT of (int)
  | IN
  | IF
  | IDENT of (string)
  | FST
  | EQ
  | EOF
  | ELSE
  | DIV
  | COMMA
  | BOOLEANQ
  | BOOL of (bool)
  | ARR
  | AND

(* This exception is raised by the monolithic API functions. *)

exception Error

(* The monolithic API. *)

val main: (Lexing.lexbuf -> token) -> Lexing.lexbuf -> (Ast.expr)
