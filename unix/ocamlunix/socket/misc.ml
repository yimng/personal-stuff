open Sys
open Unix

let try_finalize f x finally y =
  let res = try f x with exn -> finally y; raise exn in
  finally y;
  res

let iter_dir f dirname =
  let d = opendir dirname in
  try while true do f (readdir d) done
  with End_of_file -> closedir d

let rec restart_on_EINTR f x =
  try f x with Unix_error (EINTR, _, _) -> restart_on_EINTR f x

let free_children _ =
  try while fst (waitpid [ WNOHANG ] (-1)) > 0 do () done
  with Unix_error (ECHILD, _, _) -> ()

let install_tcp_server_socket addr =
  let s = socket PF_INET SOCK_STREAM 0 in
  try
    bind s addr;
    listen s 10;
    s
  with z -> close s; raise z

let tcp_server treat_connection addr =
  ignore (signal sigpipe Signal_ignore);
  let server_sock = install_tcp_server_socket addr in
  while true do
    let client = restart_on_EINTR accept server_sock in
    treat_connection server_sock client
  done

let fork_treatement server service (client_sock, _ as client) =
  let treat () = match fork () with
  | 0 -> close server; service client; exit 0
  | k -> ()
  in
  try_finalize treat () close client_sock

let double_fork_treatment server service (client_descr, _ as client) =
  let treat () = match fork () with
  | 0 ->
      if fork () <> 0 then exit 0;
      close server; service client; exit 0
  | k ->
      ignore (restart_on_EINTR (waitpid []) k)
  in
  try_finalize treat () close client_descr

<<<<<<< HEAD
let run_with_lock l f x =
  Mutex.lock l; try_finalize f x Mutex.unlock l
=======
let co_treatment server_sock service (client_descr, _ as client) =
  try ignore (Thread.create service client)
  with exn -> close client_descr; raise exn

>>>>>>> 3f118b06a940e180abe5e1219be4d0cef572a579
