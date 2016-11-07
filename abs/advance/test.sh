# As Seamus points out . . .

ArrayVar=( element0 element1 element2 {A..D} )

while read element ; do
    echo "h"
    echo "$element" 1>&2
  done <<< $(echo ${ArrayVar[*]})

  # element0 element1 element2 A B C D
