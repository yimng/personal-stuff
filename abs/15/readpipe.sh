#!/bin/sh
# readpipe.sh
# This example contributed by Bjon Eriksson.

###shopt -s lastpipe

last="(null)"
cat $0 |
while read line
do
    echo "{$line}"
    last=$line
done

echo
echo "++++++++++++++++++++++"
printf "\nAll done, last: $last\n" #  The output of this line
                                   #+ changes if you uncomment line 5.
                                   #  (Bash, version -ge 4.2 required.)

exit 0  # End of code.
        # (Partial) output of script follows.
        # The 'echo' supplies extra brackets.

#############################################

