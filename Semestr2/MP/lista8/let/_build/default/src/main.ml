open Ast

module Env = Map.Make(String)
let parse (s : string) : expr =
  Parser.main Lexer.read (Lexing.from_string s)


type value =
  | VInt of int
  | VBool of bool
  | VUnit of unit

type env = expr Env.t 

let eval_op (op : bop) (val1 : value) (val2 : value) : value =
  match op, val1, val2 with
  | Add,  VInt  v1, VInt  v2 -> VInt  (v1 + v2)
  | Sub,  VInt  v1, VInt  v2 -> VInt  (v1 - v2)
  | Mult, VInt  v1, VInt  v2 -> VInt  (v1 * v2)
  | Div,  VInt  v1, VInt  v2 -> VInt  (v1 / v2)
  | And,  VBool v1, VBool v2 -> VBool (v1 && v2)
  | Or,   VBool v1, VBool v2 -> VBool (v1 || v2)
  | Leq,  VInt  v1, VInt  v2 -> VBool (v1 <= v2)
  | Eq,   _,        _        -> VBool (val1 = val2)
  | _,    _,        _        -> failwith "type error"



let rec subst (x : ident) (s : expr) (e : expr) : expr =
  match e with
  | Binop (op, e1, e2) -> Binop (op, subst x s e1, subst x s e2)
  | If (b, t, e) -> If (subst x s b, subst x s t, subst x s e)
  | Var y -> if x = y then s else e
  | Let (y, e1, e2) ->
      Let (y, subst x s e1, if x = y then e2 else subst x s e2)
  | _ -> e

let reify (v : value) : expr =
  match v with
  | VInt a -> Int a
  | VBool b -> Bool b
  | VUnit ()-> Unit 

let rec eval (e : expr) : value =
  match e with
  | Int i -> VInt i
  | Bool b -> VBool b
  | Binop (op, e1, e2) ->
      eval_op op (eval e1) (eval e2)
  | If (b, t, e) ->
      (match eval b with
           | VBool true -> eval t
           | VBool false -> eval e
           | _ -> failwith "type error")
  | Let (x, e1, e2) ->
      eval (subst x (reify (eval e1)) e2)
  | Var x -> failwith ("unknown var " ^ x)
  | Unit -> VUnit ()    
let interp (s : string) : value =
  eval (parse s)


let cp (e:expr):expr = (*zad1*)
  let rec cp_rec (env: env) (expr: expr) = 
    match expr with 
    |Int _ -> expr
    |Bool _ -> expr
    |Var x -> (match (Env.find_opt x env) with
              |Some v -> v
              |None -> Var x)
    |Binop(op,e1,e2) -> (match (cp_rec env e1), (cp_rec env e2) with
                        |Int v1, Int v2 -> (match op with
                                           |Add -> Int(v1+v2)
                                           |Sub -> Int(v1-v2)
                                           |Mult -> Int(v1*v2)
                                           |Div -> Int(v1/v2)
                                           |Eq -> Bool(v1=v2)
                                           |Leq -> Bool(v1<=v2)
                                           |_->failwith "type error")
                        |Bool v1, Bool v2 -> (match op with
                                             |Or -> Bool(v1 || v2)
                                             |And -> Bool(v1 && v2)
                                             |Eq -> Bool(v1=v2)
                                             |_ -> failwith "type error")
                        | _, _ -> Binop(op, cp_rec env e1, cp_rec env e2))

    |If(b,t,e) -> (match cp_rec env b with
                  |Bool x -> (match x with
                             |true -> cp_rec env t
                             |false -> cp_rec env e)
                  |con -> If(con, cp_rec env t, cp_rec env e)
                  )
    |Let (x, e1, e2) -> let e1' = cp_rec env e1 in
                        (match e1' with
                        |Int _|Bool _|Unit ->
                          let env' = Env.add x e1' env in
                          cp_rec env' e2
                        |_ -> let env' = Env.add x (Var x) env in
                              Let(x, e1', cp_rec env' e2))
    |Unit -> expr
  in cp_rec Env.empty e

let alpha_equiv (e1: expr) (e2: expr): bool = (*zad2*)
  let rec alpha_rec e1 e2 env1 env2 = 
    match e1, e2 with
    |Int n1, Int n2 -> n1 = n2
    |Bool n1, Bool n2 -> n1 = n2
    |Var x1, Var x2 -> (match Env.find_opt x1 env1, Env.find_opt x2 env2 with
                        |Some y1, Some y2 -> y1 = x2 && y2 = x1
                        |None, None -> x1 = x2
                        |_ -> false)
    |Binop (bop1,e1,e2), Binop(bop2,e3,e4) -> 
      bop1 = bop2 && alpha_rec e1 e3 env1 env2 && alpha_rec e2 e4 env1 env2
    |If(con1,t1,e1), If(con2,t2,e2) -> alpha_rec con1 con2 env1 env2 &&
                                        alpha_rec t1 t2 env1 env2 &&
                                        alpha_rec e1 e2 env1 env2 
    |Let(id1, e1,e2), Let(id2,e3,e4) -> alpha_rec e1 e3 env1 env2 &&
                                        alpha_rec e2 e4 (Env.add id1 id2 env1) (Env.add id2 id1 env2)
    |Unit, Unit -> true
    |_ -> false

    in alpha_rec e1 e2 Env.empty Env.empty
let closed (e: expr):bool = 
  let rec closed_rec expr bounded =  
    match expr with
    |Binop(_,e1,e2) -> closed_rec e1 bounded && closed_rec e2 bounded
    |Int _ -> true
    |Bool _ -> true
    |If(b,t,e) -> closed_rec b bounded && closed_rec t bounded && closed_rec e bounded
    |Let(x,e1,e2) -> closed_rec e1 bounded && closed_rec e2 (x::bounded)
    |Var x -> List.mem x bounded
    |Unit -> true

  in closed_rec e []

