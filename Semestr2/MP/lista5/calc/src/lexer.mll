{
open Parser
}

let white = [' ' '\t']+
let digit = ['0'-'9']
let number = '-'? digit+ "." digit+?

rule read =
    parse
    | white { read lexbuf }
    | "*" { TIMES }
    | "+" { PLUS }
    | "-" { MINUS }
    | "/" { DIV }
    | "(" { LPAREN }
    | ")" { RPAREN }
    | "**" { POW }
    | "log" { LOG }
    | "const_e" { E_CONST }
    | number { FLOAT ( float_of_string (Lexing.lexeme lexbuf)) } (*float*)
    | eof { EOF }
