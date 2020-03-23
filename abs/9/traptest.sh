#/bin/bash
trap 'echo you hit Ctrl-C/Ctrl-\, now exiting..; exit' SIGINT SIGQUIT
count=0
 
while :
 do
   sleep 1
   count=$(expr $count + 1)
   echo $count
 done
