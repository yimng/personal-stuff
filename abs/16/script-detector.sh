#!/bin/bash
# script-detector.sh: Detects scripts within a directory.
ARGS=1
E_BADARGS=85
E_NODIR=86
if [ $# -ne "$ARGS" ]
then
    echo "Usage: `basename $0` dir"
    exit $E_BADARGS
fi
if [[ ! -d $1 ]]
then
    echo "pllease supply a dir"
    exit $E_NODIR
fi
TESTCHARS=11    # Test first 2 characters.
SHABANG='#!/bin/bash'   # Scripts begin with a "sha-bang."

for file in $1/*  # Traverse all the files in current directory.
do
  if [[ `head -c$TESTCHARS "$file"` = "$SHABANG" ]]
  #      head -c2                      #!
  #  The '-c' option to "head" outputs a specified
  #+ number of characters, rather than lines (the default).
  then
    echo "File \"$file\" is a script."
  else
    echo "File \"$file\" is *not* a script."
  fi
done
  
exit 0

#  Exercises:
#  ---------
#  1) Modify this script to take as an optional argument
#+    the directory to scan for scripts
#+    (rather than just the current working directory).
#
#  2) As it stands, this script gives "false positives" for
#+    Perl, awk, and other scripting language scripts.
#     Correct this.
