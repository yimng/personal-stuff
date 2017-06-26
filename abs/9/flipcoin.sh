#!/bin/bash
# How random is RANDOM?

RANDOM=$$       # Reseed the random number generator using script process ID.
echo "RANDOM = $RANDOM"

PIPS=2         
MAXTHROWS=500  
throw=0         

HEAD=0          
TAIL=0         

print_result ()
{
echo
echo "HEAD =   $HEAD"
echo "TAIL =   $TAIL"
echo
}

update_count()
{
case "$1" in
  0) ((HEAD++));;
  1) ((TAIL++));;
esac
}

echo


while [ "$throw" -lt "$MAXTHROWS" ]
do
  let "die1 = RANDOM % $PIPS"
  update_count $die1
  let "throw += 1"
done  

print_result

exit $?

