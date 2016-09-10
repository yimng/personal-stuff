open Unix

let input_int = input_binary_int
let output_int = output_binary_int

let generate k output =
  let rec gen m =
    output_int output m;
    if m < k then gen (m+1)
  in
  gen 2

let print_prime n = print_int n; print_newline ()

let read_first_primes input count =
  let rec read_primes first_primes count =
    if count <= 0 then first_primes else
      let n = input_int input in
      if List.exists (fun m -> n mod m = 0) first_primes then
        read_primes first_primes count
      else begin
        print_prime n;
        read_primes (n :: first_primes) (count - 1)
      end
  in
  read_primes [] count

let rec filter input =
  try
    let first_primes = read_first_primes input 1000 in
    let (fd_in, fd_out) = pipe () in
    let p = Thread.create filter (in_channel_of_descr fd_in) in
    let output = out_channel_of_descr fd_out in
    try
      while true do
        let n = input_int input in
        if List.exists (fun m -> mod m = 0) first_primes then ()
        else output_int output n
      done;
    with End_of_file ->
      close_out output;
      Thread.join p
  with End_of_file -> ()

let sieve () =
  let len = try int_of_string Sys.argv.(1) with _ -> max_int in
  let (fd_in, fd_out) = pipe () in

  let k = Thread.create filter (in_channel_of_descr fd_in) in
  let output = out_channel_of_descr fd_out in
  generate len output;
  close_out output;
  Thread.join k
let () = handle_unix_error sieve ()


