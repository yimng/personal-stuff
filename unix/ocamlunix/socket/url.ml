open Unix

exception Error of string
let error err mes = raise (Error (err ^ ": " ^ mes))
let handle_error f x = try f x with Error err -> prerr_endline err; exit 2

let default_port = "80"

type regexp = { regexp : Str.regexp; fields : (int * string option) list; }

let regexp_match r string =
  let get (pos, default) =
    try Str.matched_group pos string
    with Not_found ->
      match default with Some s -> s | _ -> raise Not_found in
  try
    if Str.string_match r.regexp string 0 then
      Some (List.map get r.fields)
    else None
  with Not_found -> None

let host_regexp =
  { regexp = Str.regexp "\\([^/:]*\\)\\(:\\([0-9]+\\)\\)?";
    fields = [ 1, None; 3, Some default_port; ] }

let url_regexp =
  { regexp = Str.regexp "http://\\([^/:]*\\(:[0-9]+\\)?\\)\\(/.*\\)";
    fields = [ 1, None; 3, None ] }

let parse_host host = match regexp_match host_regexp host with
  | Some (host :: port :: _) -> host, int_of_string port
  | _ -> error host "Ill formed host"

let parse_url url = match regexp_match url_regexp url with
  | Some (host :: path :: _) -> parse_host host, path
  | _ -> error url "Ill formed url"


let send_get url sock =
    let s = Printf.sprintf "GET %s\r\n" url in
    ignore (write sock s 0 (String.length s))

let get_url proxy url fdout =
  let (hostname, port), path = match proxy with
    | None -> parse_url url
    | Some host -> parse_host host, url
  in
  let hostaddr =
    try inet_addr_of_string hostname
    with Failure _ ->
      try (gethostbyname hostname).h_addr_list.(0)
      with Not_found -> error hostname "Host not found"
  in
  let sock = socket PF_INET SOCK_STREAM 0 in
  Misc.try_finalize
    begin function () ->
      connect sock (ADDR_INET (hostaddr, port));
      send_get path sock;
      Misc.retransmit sock fdout
    end ()
    close sock

let geturl () =
  let len =  Array.length Sys.argv in
  if len < 2 then
    error "Usage:" (Sys.argv.(0) ^ " [ proxy [:<port>] ] <url>")
  else
    let proxy, url =
      if len > 2 then Some Sys.argv.(1), Sys.argv.(2) else
      None, Sys.argv.(1)
    in
    get_url proxy url stdout

let _ = handle_unix_error (handle_error geturl) ()

