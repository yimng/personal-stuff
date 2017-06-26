open Sys
open Unix

let server () =
  if Array.length Sys.argv < 2 then begin
    prerr_endline "Usage: client <port> <command> [arg1...argn]";
    exit 2;
  end;
  let port = int_of_string Sys.argv.(1) in
  let args = Array.sub Sys.argv 2 (Array.length Sys.argv - 2) in
  let host = (gethostbyname(gethostname ())).h_addr_list.(0) in
  let addr = ADDR_INET (host, port) in
  let treat sock (client_sock, client_addr as client) =
    (*log information*)
    begin match client_addr with
    | ADDR_INET(caller, _) ->
        prerr_endline ("Connection from " ^ string_of_inet_addr caller);
    | ADDR_UNIX _ ->
        prerr_endline "Connection from the Unix domain (???)";
    end;
    (*connection treatment*)
    let service (s, _) =
      dup2 s stdin; dup2 s stdout; dup2 s stderr; close s;
      execvp args.(0) args
    in
    Misc.double_fork_treatment sock service client in
  Misc.tcp_server treat addr

let _ = handle_unix_error server ()
