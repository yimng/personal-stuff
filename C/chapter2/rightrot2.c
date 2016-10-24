unsigned rightword(unsigned x, int n)
{
	int wordlength(void);
	unsigned rbits;
	if ((n = n % wordlenght()) > 0)
	{
		rbits = ~(~0 << n) & x;

		rbits = rbits << (wordlength() - n);
		x = x >> n;
		x = x | rbits;
	}
	return x;

}
