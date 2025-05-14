open Ast

let parse (s : string) : expr =
  Parser.main Lexer.read (Lexing.from_string s)

module M = Map.Make(String)

module Loc = Int
module H = Map.Make(Loc)

let fresh h = H.cardinal h

type env = value M.t

and value =
  | VInt of int
  | VBool of bool
  | VUnit
  | VPair of value * value
  | VClosure of ident * expr * env
  | VRecClosure of ident * ident * expr * env
  | VRef of Loc.t
  | VContinue
  | VBreak

type heap = value H.t

let rec show_value v =
  match v with
  | VInt n -> string_of_int n
  | VBool v -> string_of_bool v
  | VUnit -> "()"
  | VPair (v1,v2) -> "(" ^ show_value v1 ^ ", " ^ show_value v2 ^ ")"
  | VClosure _ | VRecClosure _ -> "<fun>"
  | VRef _ -> "<ref>"
  | VBreak -> "<Break>"
  | VContinue -> "<Continue>"

let int_op op v1 v2 h =
  match v1, v2 with
  | VInt x, VInt y -> (VInt (op x y), h)
  | _ -> failwith "type error"

let cmp_op op v1 v2 h =
  match v1, v2 with
  | VInt x, VInt y -> (VBool (op x y), h)
  | _ -> failwith "type error"

let bool_op op v1 v2 h =
  match v1, v2 with
  | VBool x, VBool y -> (VBool (op x y), h)
  | _ -> failwith "type error"

let eval_op (op : bop) : value -> value -> heap -> value * heap =
  match op with
  | Add  -> int_op ( + )
  | Sub  -> int_op ( - )
  | Mult -> int_op ( * )
  | Div  -> int_op ( / )
  | And  -> bool_op ( && )
  | Or   -> bool_op ( || )
  | Eq   -> (fun v1 v2 h -> (VBool (v1 = v2), h))
  | Neq  -> (fun v1 v2 h -> (VBool (v1 <> v2), h))
  | Leq  -> cmp_op ( <= )
  | Lt   -> cmp_op ( < )
  | Geq  -> cmp_op ( >= )
  | Gt   -> cmp_op ( > )
  | Assgn -> (fun v1 v2 h ->
      match v1 with
      | VRef r -> (VUnit, H.add r v2 h)
      | _ -> failwith "type error")

let rec eval_env (env : env) (e : expr) (h : heap) : value * heap =
  match e with
  | Int i -> (VInt i, h)
  | Bool b -> (VBool b, h)
  | Binop (op, e1, e2) ->
      let (v1, h) = eval_env env e1 h in
      let (v2, h) = eval_env env e2 h in
      eval_op op v1 v2 h
  | If (b, t, e) ->
      let (v, h) = eval_env env b h in
      (match v with
      | VBool true -> eval_env env t h
      | VBool false -> eval_env env e h
      | _ -> failwith "type error")
  | Var x ->
      let v =
        match M.find_opt x env with
        | Some v -> v
        | None -> failwith "unknown var"
      in
      (v, h)
  | Let (x, e1, e2) ->
      let (v1, h) = eval_env env e1 h in
      eval_env (env |> M.add x v1) e2 h
  | Pair (e1, e2) ->
      let (v1, h) = eval_env env e1 h in
      let (v2, h) = eval_env env e2 h in
      (VPair (v1, v2), h)
  | Unit -> (VUnit, h)
  | Fst e ->
      let (v, h) = eval_env env e h in
      (match v with
      | VPair (v1, _) -> (v1, h)
      | _ -> failwith "Type error")
  | Snd e ->
      let (v, h) = eval_env env e h in
      (match v with
      | VPair (_, v2) -> (v2, h)
      | _ -> failwith "Type error")
  | Match (e1, x, y, e2) ->
      let (v1, h) = eval_env env e1 h in
      (match v1 with
      | VPair (v1, v2) ->
        eval_env (env |> M.add x v1 |> M.add y v2) e2 h
      | _ -> failwith "Type error")
  | IsPair e ->
      let (v, h) = eval_env env e h in
      let v =
        match v with
        | VPair _ -> VBool true
        | _ -> VBool false
      in
      (v, h)
  | Fun (x, e) -> (VClosure (x, e, env), h)
  | Funrec (f, x, e) -> (VRecClosure (f, x, e, env), h)
  | App (e1, e2) ->
      let (v1, h) = eval_env env e1 h in
      let (v2, h) = eval_env env e2 h in
      (match v1 with
        | VClosure (x, body, clo_env) ->
            eval_env (M.add x v2 clo_env) body h
        | VRecClosure (f, x, body, clo_env) as c ->
            eval_env (clo_env |> M.add x v2 |> M.add f c) body h
        | _ -> failwith "not a function")
  | Ref e ->
      let (v, h) = eval_env env e h in
      let r = fresh h in
      (VRef r, H.add r v h)
  | Deref e ->
      let (v, h) = eval_env env e h in
      (match v with
        | VRef r -> (H.find r h, h)
        | _ -> failwith "not a reference")
  | While (cond_expr, body_expr) ->
    let rec eval_loop curr_heap =
      let (cond_val, heap_after_cond) = eval_env env cond_expr curr_heap in
      match cond_val with
      | VBool true ->
          let (body_val, heap_after_body) = eval_env env body_expr heap_after_cond in
          (match body_val with
            | VBreak -> (VUnit, heap_after_body)
            | VContinue -> eval_loop heap_after_body
            | _ -> eval_loop heap_after_body)
      | VBool false -> (VUnit, heap_after_cond)
      | _ -> failwith "while: condition must be boolean"
    in
    eval_loop h
  | Break -> (VBreak, h)
  | Continue -> (VContinue, h)
    
    


let eval e = fst (eval_env M.empty e H.empty)

let interp (s : string) : value =
  eval (parse s)
