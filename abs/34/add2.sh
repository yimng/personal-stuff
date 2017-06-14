add2 ()
{
  echo "Whatever ... "   # Delete this line!
  let "retval = $1 + $2"
    echo $retval
    }

    num1=12
    num2=43
    echo "Sum of $num1 and $num2 = $(add2 $num1 $num2)"

#   Sum of 12 and 43 = Whatever ... 
#   55

#        The "echoes" concatenate.
