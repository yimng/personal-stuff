#!/bin/bash
# arrow-detect.sh: Detects the arrow keys, and a few more.
# Thank you, Sandro Magi, for showing me how.
#set -x
# --------------------------------------------

#  Mark Alexander came up with a simplified
#+ version of the above script (Thank you!).
#  It eliminates the need for grep.


  uparrow=$'\x1b[A'
  downarrow=$'\x1b[B'
  leftarrow=$'\x1b[D'
  rightarrow=$'\x1b[C'
  home=$'\x1b[1'
  insert=$'\x1b[2'
  delete=$'\x1b[3'
  end=$'\x1b[4'
  pgup=$'\x1b[5'
  pgdown=$'\x1b[6'

  read -s -n3 -p "Hit an arrow key: " x

  case "$x" in
  $uparrow)
     echo "You pressed up-arrow"
     ;;
  $downarrow)
     echo "You pressed down-arrow"
     ;;
  $leftarrow)
     echo "You pressed left-arrow"
     ;;
  $home)
     echo "You pressed home"
     ;;
  $end)
     echo "You pressed end"
     ;;
  $insert)
     echo "You pressed insert"
     ;;
  $delete)
     echo "You pressed delete"
     ;;
  $pgup)
     echo "You pressed pgup"
     ;;
  $pgdown)
     echo "You pressed pgdown"
     ;;
  esac

exit $?
