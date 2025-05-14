
%token <float> FLOAT (*float*)
%token PLUS
%token MINUS
%token TIMES
%token DIV
%token POW (*zad6*)
%token LOG (*zad6*)
%token LPAREN
%token RPAREN
%token E_CONST (*zad6*)
%token EOF

%start <Ast.expr> main

%left PLUS MINUS
%left TIMES DIV
%right POW (*zad6*)
%right LOG (*zad6*)

%%

main:
    | e = expr; EOF { e }
    ;

expr:
    | i = FLOAT { Float i } (*float*)
    | e1 = expr; PLUS; e2 = expr { Binop(Add, e1, e2) }
    | e1 = expr; MINUS; e2 = expr { Binop(Sub, e1, e2) }
    | e1 = expr; DIV; e2 = expr { Binop(Div, e1, e2) }
    | e1 = expr; TIMES; e2 = expr { Binop(Mult, e1, e2) }
    | e1 = expr; POW; e2 = expr { Binop(Pow,e1,e2) } (*zad6*)
    | LOG; e1 = expr { Pre(Log, e1) } (*zad6*)
    | LPAREN; e = expr; RPAREN { e }
    | E_CONST {Float 2.71828}
    ;


