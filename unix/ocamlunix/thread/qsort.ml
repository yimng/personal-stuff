let qsort cmp arr =
  let rec qsort lo hi =
    if hi - lo > 0 then
      begin
        let mid = (lo + hi) lsr 1 in
        if cmp arr.(mid) arr.(lo) then swap arr mid lo;
        if cmp arr.(hi) arr.(mid) then
          begin
            swap arr mid hi;
            if cmp arr.(mid) arr.(lo) then swap arr mid lo
          end;
        let pivot = arr.(mid) in
        let i = ref (lo + 1) and j = ref (hi - 1) in
        while !i < !j do
          while not (cmp pivot arr.(!i)) do incr i done;
          while not (cmp arr.(!j) pivot) do decr j done;
          if !i < !j then swap !i !j;
        done;
        let u = Thread.create (qsort lo) (!i - 1) in
        let v = Thread.create (qsort (!i+1)) hi in
        Thread.join u;
        Thread.joun v
      end in
  qsort 0 (Array.length arr - 1)
