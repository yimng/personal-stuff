open Unix

let rec really_read fd buff start length =
  if length <= 0 then () else
    match read fd buff start length with
    | 0 -> raise End_of_file
    | n -> really_read fd buff (start + n) (length - n)

let buffer = String.create 258

let multiplex channel inputs outputs = 
  let input_fds = channel :: Array.to_list inputs in
  try
    while true do
      let (ready_fds, _, _) = select input_fds [] [] (-1.0) in
      for i = 0 to Array.length inputs - 1 do
        if List.mem inputs.(i) ready_fds then begin
          let n = read inputs.(i) buffer 2 255 in
          buffer.[0] <- char_of_int i;
          buffer.[1] <- char_of_int n;
          ignore (write channel buffer 0 (n + 2));
          ()
        end
      done;
      if List.mem channel ready_fds then begin
        really_read channel buffer 0 2;
        let i = int_of_char (buffer.[0])
        and n = int_of_char (buffer.[1]) in
        if n = 0 then close outputs.(i) else begin
        really_read channel buffer 0 n;
        ignore (write outputs.(i) buffer 0 n);
        end
      end
    done
  with End_of_file -> ();;

