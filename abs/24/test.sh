#!/bin/bash
f1 ()
{
	echo "inside f1"
	f2()
	{
		echo "Function \"f2\", inside \"f1\"."
	}
}
f2
echo
f1
f2

