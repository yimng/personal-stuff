#!/bin/bash
# Changes a file to all uppercase.

E_BADARGS=85

if [ -z "$1" ]  # Standard check for command-line arg.
then
  echo "Usage: `basename $0` filename"
  exit $E_BADARGS
fi  
PS3="select upper(u) or lower(l):"
select c in "uppercase" "lowercase"
do
    case "$c" in
        "uppercase")
        tr a-z A-Z <"$1"
        ;;
        "lowercase")
        tr A-Z a-z <"$1"
        ;;
    esac
    break
done

# Same effect as above, but using POSIX character set notation:
#        tr '[:lower:]' '[:upper:]' <"$1"
# Thanks, S.C.

#     Or even . . .
#     cat "$1" | tr a-z A-Z
#     Or dozens of other ways . . .

exit 0

#  Exercise:
#  Rewrite this script to give the option of changing a file
#+ to *either* upper or lowercase.
#  Hint: Use either the "case" or "select" command.
