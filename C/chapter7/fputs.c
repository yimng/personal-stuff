int fputs(char *s, FILE *iop)
{
	int c;
	while (c = *s++)
		putc(c, iop);
	return ferror(iop) ? EOF : 1;
}
