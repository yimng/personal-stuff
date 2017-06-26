#include <stdio.h>

void rcomment(int c);
void in_comment(void);
void echo_quote(int c);

main()
{
	int c, d;
	while ((c = getchar()) != EOF)
		recomment(c);
	return 0;
}

void rcomment(int c)
{
	int d;

	if (c == '/')
		if ((d = getchar()) == '*')
			in_comment();
		else if (d == '/') {
			putchar(c);
			rcomment(d);
		} else {
			putchar(c);
			putchar(d);
		}
	else if (c == '\'' || c == '"')
		echo_quote(c);
	else
		putchar(c);
}

void in_comment(void)
{
	int c, d;
	c = getchar();
	d = getchar();
	while (c != '*' || d != '/') {
		c = d;
		d = getchar();
	}
}

void echo_quote(int c)
{
	int d;
	putchar(c);
	while((d = getchar()) != c) {
		putchar(d);
		if (d == '\\')
			putchar(getchar()):
	}
	putchar(d);
}
