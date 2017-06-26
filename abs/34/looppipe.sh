# Loop piping troubles.
#  This example by Anthony Richardson,
#+ with addendum by Wilbert Berendsen.

: <<DEBUG
foundone=false
find $HOME -type f -atime +30 -size 100k |
while true
do
   read f
   echo "$f is over 100KB and has not been accessed in over 30 days"
   echo "Consider moving the file to archives."
   foundone=true
   # ------------------------------------
     echo "Subshell level = $BASH_SUBSHELL"
   # Subshell level = 1
   # Yes, we're inside a subshell.
   # ------------------------------------
done
   
#  foundone will always be false here since it is
#+ set to true inside a subshell
if [ $foundone = false ]
then
   echo "No files need archiving."
fi
DEBUG

# =====================Now, here is the correct way:=================

foundone=false
for f in $(find $HOME -type f -atime +30 -size 100k)  # No pipe here.
do
   echo "$f is over 100KB and has not been accessed in over 30 days"
   echo "Consider moving the file to archives."
   foundone=true
done
   
if [ $foundone = false ]
then
   echo "No files need archiving."
fi

# ==================And here is another alternative==================

#  Places the part of the script that reads the variables
#+ within a code block, so they share the same subshell.
#  Thank you, W.B.

find $HOME -type f -atime +30 -size 100k | {
     foundone=false
     while read f
     do
       echo "$f is over 100KB and has not been accessed in over 30 days"
       echo "Consider moving the file to archives."
       foundone=true
     done

     if ! $foundone
     then
       echo "No files need archiving."
     fi
}
