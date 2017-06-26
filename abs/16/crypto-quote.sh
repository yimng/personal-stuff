#!/bin/bash
# crypto-quote.sh: Encrypt quotes
NO_ARGS=0
E_OPTERROR=85

if [ $# -eq "$NO_ARGS" ]
then
  echo "Usage: `basename $0` options (-de)"
  exit E_OPTERROR
fi
#  Will encrypt famous quotes in a simple monoalphabetic substitution.
#  The result is similar to the "Crypto Quote" puzzles
#+ seen in the Op Ed pages of the Sunday paper.

key=ETAOINSHRDLUBCFGJMQPVWZYXK

# The "key" is nothing more than a scrambled alphabet.
# Changing the "key" changes the encryption.

# The 'cat "$@"' construction gets input either from stdin or from files.
# If using stdin, terminate input with a Control-D.
# Otherwise, specify filename as command-line parameter.

while getopts ":de" Option
do
  case $Option in
    e ) 
      shift $(($OPTIND - 1))
      echo -n "-encrypt: "
      echo "$@"
      cat "$@" | tr "a-z" "A-Z" | tr "A-Z" "$key"
      ;;
    d )
      shift $(($OPTIND - 1))
      echo -n "-decrypt: "
      echo "$@"
      cat "$@" | tr "a-z" "A-Z" | tr "$key" "A-Z"
      ;;
  esac
done
#        |  to uppercase  |     encrypt       
# Will work on lowercase, uppercase, or mixed-case quotes.
# Passes non-alphabetic characters through unchanged.


# Try this script with something like:
# "Nothing so needs reforming as other people's habits."
# --Mark Twain
#
# Output is:
# "CFPHRCS QF CIIOQ MINFMBRCS EQ FPHIM GIFGUI'Q HETRPQ."
# --BEML PZERC

# To reverse the encryption:
# cat "$@" | tr "$key" "A-Z"


#  This simple-minded cipher can be broken by an average 12-year old
#+ using only pencil and paper.

exit 0

#  Exercise:
#  --------
#  Modify the script so that it will either encrypt or decrypt,
#+ depending on command-line argument(s).
