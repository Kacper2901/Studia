open Ast

let parse (s : string) : expr =
  Parser.main Lexer.read (Lexing.from_string s)

module M = Map.Make(String)

type env = value M.t

and value =
  | VInt of int
  | VBool of bool
  | VUnit
  | VPair of value * value
  | VClosure of ident * expr * env
  | VRecClosure of ident * ident * expr * env
  | VRef of value ref

let rec show_value v =
  match v with
  | VInt n -> string_of_int n
  | VBool v -> string_of_bool v
  | VUnit -> "()"
  | VPair (v1,v2) -> "(" ^ show_value v1 ^ ", " ^ show_value v2 ^ ")"
  | VClosure _ | VRecClosure _ -> "<fun>"
  | VRef r -> "{ contents = " ^ show_value !r ^ " }"

let int_op op v1 v2 =
  match v1, v2 with
  | VInt x, VInt y -> VInt (op x y)
  | _ -> failwith "type error"

let cmp_op op v1 v2 =
  match v1, v2 with
  | VInt x, VInt y -> VBool (op x y)
  | _ -> failwith "type error"

let bool_op op v1 v2 =
  match v1, v2 with
  | VBool x, VBool y -> VBool (op x y)
  | _ -> failwith "type error"

let eval_op (op : bop) : value -> value -> value =
  match op with
  | Add  -> int_op ( + )
  | Sub  -> int_op ( - )
  | Mult -> int_op ( * )
  | Div  -> int_op ( / )
  | And  -> bool_op ( && )
  | Or   -> bool_op ( || )
  | Eq   -> (fun v1 v2 -> VBool (v1 = v2))
  | Neq  -> (fun v1 v2 -> VBool (v1 <> v2))
  | Leq  -> cmp_op ( <= )
  | Lt   -> cmp_op ( < )
  | Geq  -> cmp_op ( >= )
  | Gt   -> cmp_op ( > )
  | Assgn -> (fun v1 v2 ->
      match v1 with
      | VRef r -> r := v2; VUnit
      | _ -> failwith "type error")

let rec eval_env (env : env) (e : expr) : value =
  match e with
  | Int i -> VInt i
  | Bool b -> VBool b
  | Binop (op, e1, e2) ->
      let v1 = eval_env env e1 in
      let v2 = eval_env env e2 in
      eval_op op v1 v2
  | If (b, t, e) ->
      let v = eval_env env b in
      (match v with
      | VBool true -> eval_env env t
      | VBool false -> eval_env env e
      | _ -> failwith "type error")
  | Var x ->
      let v =
        match M.find_opt x env with
        | Some v -> v
        | None -> failwith "unknown var"
      in
      v
  | Let (x, e1, e2) ->
      let v1 = eval_env env e1 in
      eval_env (env |> M.add x v1) e2
  | Pair (e1, e2) ->
      let v1 = eval_env env e1 in
      let v2 = eval_env env e2 in
      VPair (v1, v2)
  | Unit -> VUnit
  | Fst e ->
      let v = eval_env env e in
      (match v with
      | VPair (v1, _) -> v1
      | _ -> failwith "Type error")
  | Snd e ->
      let v = eval_env env e in
      (match v with
      | VPair (_, v2) -> v2
      | _ -> failwith "Type error")
  | Match (e1, x, y, e2) ->
      let v1 = eval_env env e1 in
      (match v1 with
      | VPair (v1, v2) ->
        eval_env (env |> M.add x v1 |> M.add y v2) e2
      | _ -> failwith "Type error")
  | IsPair e ->
      let v = eval_env env e in
      (match v with
        | VPair _ -> VBool true
        | _ -> VBool false)
  | Fun (x, e) -> VClosure (x, e, env)
  | Funrec (f, x, e) -> VRecClosure (f, x, e, env)
  | App (e1, e2) ->
      let v1 = eval_env env e1 in
      let v2 = eval_env env e2 in
      (match v1 with
        | VClosure (x, body, clo_env) ->
            eval_env (M.add x v2 clo_env) body
        | VRecClosure (f, x, body, clo_env) as c ->
            eval_env (clo_env |> M.add x v2 |> M.add f c) body
        | _ -> failwith "not a function")
  | Ref e ->
      let v = eval_env env e in
      VRef (ref v)
  | Deref e ->
      let v = eval_env env e in
      (match v with
        | VRef r -> !r
        | _ -> failwith "not a reference")

let eval = eval_env M.empty

let interp (s : string) : value =
  eval (parse s)
