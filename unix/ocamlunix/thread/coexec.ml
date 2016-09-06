exception Exited

type 'a result = Value of 'a | Exception of exn
let eval f x = try Value (f x) with z -> Exception z
let coexec (f : 'a -> 'b) (x : 'a) : unit -> 'b =
  let result = ref (Exception Exited) in
  let p = Thread.create (fun x -> result := eval f x) x in
  function () -> match (Thread.join p; !result) with
    | Value v -> v
    | Exception exn -> raise exn

let re = let v1 = coexec succ 4 and v2 = coexec succ 5 in v1 () + v2 ()
let _ = print_int re; print_newline;
