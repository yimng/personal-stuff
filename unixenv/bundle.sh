#bundle: group files into destribution package

echo '# To unbundle, sh this file'
for i
do
	echo "echo $i 1>&2"
	echo "cat >$i <<'End of $i'"
	cat $i
	echo "End of $i"
done

