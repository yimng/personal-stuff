#!/bin/bash

# Antonio Macchi has a simpler alternative.

while true
do
  read -sn1 a
  test "$a" == `echo -en "\e"` || continue
  read -sn1 a
  test "$a" == "[" || continue
  read -sn1 a
  case "$a" in
    A)  echo "up";;
    B)  echo "down";;
    C)  echo "right";;
    D)  echo "left";;
    1)  echo "home";;
    2)  echo "insert";;
    3)  echo "delete";;
    4)  echo "end";;
    5)  echo "pgup";;
    6)  echo "pgdown";;
  esac
done

# ========================================= #

#  Exercise:
#  --------
#  1) Add detection of the "Home," "End," "PgUp," and "PgDn" keys.
