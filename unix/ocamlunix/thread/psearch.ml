exception Found
let simple_search cond v =
  try
    for i = 0 to Array.length v - 1 do
      if cond v.(i) then raise Found
    done;
    false
  with Found -> true

let rec psearch k cond v =
  let n = Array.length v in
  let slice i = Array.sub v (i * k) (min k (n - i * k)) in
  let slices = Array.init (n/k) slice in
  let found = ref false in
  let pcond v = if !found then Thread.exit (); cond v in
  let search v = if simple_search pcond v then found := true in
  let proc_list = Array.map (Thread.create search) slices in
  Array.iter Thread.join proc_list;
  !found

