open Unix;;
open Printf;;

let split_words s =
 let rec skip_blanks i =
   if i < String.length s & s.[i] = ' '
   then skip_blanks (i+1)
   else i in
 let rec split start i =
   if i >= String.length s then
     [String.sub s start (i-start)]
   else if s.[i] = ' ' then
     let j = skip_blanks i in
     String.sub s start (i-start) :: split j j
   else
     split start (i+1) in
 Array.of_list (split 0 0);;

let exec_command cmd =
 try execvp cmd.(0) cmd
 with Unix_error(err, _, _) ->
   printf "Cannot execute %s : %s\n%!"
     cmd.(0) (error_message err);
   exit 255

let print_status program status =
 match status with
 | WEXITED 255 -> ()
 | WEXITED status ->
     printf "%s exited with code %d\n%!" program status;
 | WSIGNALED signal ->
     printf "%s killed by signal %d\n%!" program signal;
 | WSTOPPED signal ->
     printf "%s stopped (???)\n%!" program;;

let minishell () =
 try
   while true do
     let cmd = input_line Pervasives.stdin in
     let words = split_words cmd in
     match fork () with
     | 0 -> exec_command words
     | pid_son ->
         let pid, status = wait () in
         print_status "Program" status
   done
 with End_of_file -> ()
;;

handle_unix_error minishell ();;
