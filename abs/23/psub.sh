#!/bin/bash
# psub.bash
# As inspired by Diego Molina (thanks!).

declare -a array0
while read
do
  echo "$REPLY"
  echo ${#array0[@]}
  array0[${#array0[@]}]="$REPLY"
done < <( sed -e 's/bash/CRASH-BANG!/' $0 | grep bin | awk '{print $1}' )
#  Sets the default 'read' variable, $REPLY, by process substitution,
#+ then copies it into an array.

echo "${array0[@]}"

exit $?
