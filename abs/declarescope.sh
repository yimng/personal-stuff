foo ()
{
declare FOO="bar"
}

bar ()
{
foo
echo $FOO
}

bar   # Prints bar.
