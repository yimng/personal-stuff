type unr_op = UMINUS | NOT
type bin_op = PLUS | MINUS | MULT | DIV | MOD  
             | EQUAL | LESS | LESSEQ | GREAT | GREATEQ | DIFF 
                          | AND | OR

type expression = 
     ExpInt of int 
   | ExpVar of string
   | ExpStr of string 
   | ExpUnr of unr_op * expression
   | ExpBin of expression * bin_op * expression

type command = 
     Rem of string
   | Goto of int 
   | Print of expression
   | Input of string 
   | If of expression * int 
   | Let of string * expression

type line = { num : int ; cmd : command }

type program = line list

type phrase =  Line of line | List | Run | PEnd

let priority_uop = function NOT -> 1 | UMINUS -> 7
let priority_binop = function 
     MULT | DIV  -> 6
   | PLUS | MINUS -> 5
   | MOD -> 4
   | EQUAL | LESS | LESSEQ | GREAT | GREATEQ | DIFF -> 3
   | AND | OR -> 2

let pp_binop = function  
     PLUS -> "+" | MULT -> "*" | MOD   -> "%" | MINUS -> "-" 
   | DIV -> "/"  | EQUAL -> " = " | LESS -> " < " 
   | LESSEQ -> " <= "   | GREAT   -> " > " 
   | GREATEQ -> " >= "   | DIFF  -> " <> " | AND -> " & " | OR -> " | "  

let pp_unrop = function UMINUS -> "-"  | NOT -> "!"

let parenthesis x = "(" ^ x ^ ")"

let pp_expression = 
  let rec ppl pr = function 
       ExpInt n -> (string_of_int n)
     | ExpVar v -> v 
     | ExpStr s -> "\"" ^ s ^ "\"" 
     | ExpUnr (op,e) -> 
         let res = (pp_unrop op)^(ppl (priority_uop op) e) 
         in if pr=0 then res else parenthesis res 
     | ExpBin (e1,op,e2) -> 
         let pr2 = priority_binop op
         in let res = (ppl pr2 e1)^(pp_binop op)^(ppr pr2 e2)
         (* parenthesis if priority is not greater *)
         in if pr2 >= pr then res else parenthesis res
   and ppr pr exp = match exp with 
     (*  right subtrees only differ for binary operators *)
       ExpBin (e1,op,e2) -> 
         let pr2 = priority_binop op
         in let res = (ppl pr2 e1)^(pp_binop op)^(ppr pr2 e2)
         in if pr2 > pr then res else parenthesis res
     | _ -> ppl pr exp
   in ppl 0
