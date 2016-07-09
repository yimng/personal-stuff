strindex(s, t)
	char *s, *t;
{
	int i, n;

	n = strlen(t);
	for (i = 0; s[i] != '\n'; i++)
		if (strncmp(s+i, t, n) == 0)
			return i;
	return -1;
}
