#include <stdio.h>
#include <ctype.h>
main()
{
	int c;
	while ((c = getchar()) != EOF)
		if (isascii(c) &&
			isprint(c))
			putchar(c);
		else
			printf("\\%03o", c);
	exit(0);
}
