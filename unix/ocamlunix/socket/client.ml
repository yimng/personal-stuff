open Sys
open Unix
let retransmit fdin fdout =
	let buffer_size = 4096 in
	let buffer = String.create buffer_size in
	let rec copy () = match read fdin buffer 0 buffer_size with
		 | 0 -> ()
		 | n -> ignore (write fdout buffer 0 n); copy ()
	in
	copy ()

let client () =
	if Array.length Sys.argv < 3 then begin
		prerr_endline "Usage: client <host> <port>";
		exit 2;
	end;
	let server_name = Sys.argv.(1)
	and port_number = int_of_string Sys.argv.(2) in
	let server_addr =
		try (gethostbyname server_name).h_addr_list.(0)
		with Not_found ->
			prerr_endline (server_name ^ ": Host not found");
			exit 2 in
	let sock = socket PF_INET SOCK_STREAM 0 in
	connect sock (ADDR_INET(server_addr, port_number));
	match fork () with
	| 0 ->
		retransmit stdin sock;
		shutdown sock SHUTDOWN_SEND;
		exit 0
	| _ ->
		retransmit sock stdout;
		close stdout;
		wait ()
let _ = handle_unix_error client ()

