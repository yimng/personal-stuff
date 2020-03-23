#!/bin/bash
# findstring.sh:
# Find a particular string in the binaries in a specified directory.

E_BADARGS=65
E_NOFOLDER=66
E_NOFILE=67

if [ $# -ne 2 ]
then
  echo "Usage: `basename $0` search_string folder"
  exit $E_BADARGS
fi

if [ ! -d $2 ]
then
  echo "Please supply the folder"
  exit $E_NOFOLDER
fi
if [  -z "$1" ]
then
  echo "Please supply the search_string"
  exit $E_NOFILE
fi

directory="$2"
fstring="$1"  # See which files come from the FSF.

for file in $( find $directory -type f -name '*' | sort )
do
  strings -f $file | grep "$fstring" | sed -e "s%$directory%%"
  #  In the "sed" expression,
  #+ it is necessary to substitute for the normal "/" delimiter
  #+ because "/" happens to be one of the characters filtered out.
  #  Failure to do so gives an error message. (Try it.)
done  

exit $?

#  Exercise (easy):
#  ---------------
#  Convert this script to take command-line parameters
#+ for $directory and $fstring.

