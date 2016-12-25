#!/bin/bash
# ud.sh: UNIX to DOS text file converter.

E_WRONGARGS=85

if [ -z "$1" ]
then
  echo "Usage: `basename $0` filename-to-convert"
  exit $E_WRONGARGS
fi

NEWFILENAME=$1.dos

sed 's/$/^M/' < $1 > $NEWFILENAME
# sed $'s/$/\r/' < $1 > $NEWFILENAME
echo "Original UNIX text file is \"$1\"."
echo "Converted DOS text file is \"$NEWFILENAME\"."

exit 0
