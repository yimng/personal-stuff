#!/bin/bash
# echo-params.sh

# Call this script with a few command-line parameters.
# For example:
#     sh echo-params.sh first second third fourth fifth

params=$#              # Number of command-line parameters.
param=1                # Start at first command-line param.

while [ "$param" -le "$params" ]
do
  echo -n "Command-line parameter "
  echo -n \$$param     #  Gives only the *name* of variable.
#         ^^^          #  $1, $2, $3, etc.
                       #  Why?
                       #  \$ escapes the first "$"
                       #+ so it echoes literally,
                       #+ and $param dereferences "$param" . . .
                       #+ . . . as expected.
  echo -n " = "
  eval echo \$$param   #  Gives the *value* of variable.
# ^^^^      ^^^        #  The "eval" forces the *evaluation*
                       #+ of \$$
                       #+ as an indirect variable reference.

(( param ++ ))         # On to the next.
done

exit $?

