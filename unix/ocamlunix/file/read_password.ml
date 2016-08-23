open Unix
let read_passwd message =
  match
    try
      let default = tcgetattr stdin in
      let silent =
        { default with
          c_echo = false;
          c_echoe = false;
          c_echok = false;
          c_echonl = false;
        } in
      Some (default, silent)
    with _ -> None
  with
  | None -> input_line Pervasives.stdin
  | Some (default, silent) ->
      print_string message;
      flush Pervasives.stdout;
      tcsetattr stdin TCSANOW silent;
      try
        let s = input_line Pervasives.stdin in
        tcsetattr stdin TCSANOW default; s
      with x ->
        tcsetattr stdin TCSANOW default; raise x;;
