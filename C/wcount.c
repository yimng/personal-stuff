#include <stdio.h>
main()
{
	int c, whitespace, tab, nl;
	whitespace = 0;
	tab = 0;
	nl = 0;
	while ((c = getchar()) != EOF) {
		if (c == '\n')
			++nl;
		if (c == ' ')
			++whitespace;
		if (c == '\t')
			++tab;
	}

	printf("whitespace = %d\n, newline = %d\n, tab = %d\n", whitespace, nl, tab);
}
