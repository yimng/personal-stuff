#include <stdio.h>

#define MAXLINE		100
#define TABINC		8
#define YES		1
#define NO		0

void settab(int argc, char *argv[], char *tab);
void detab(char *tab);
int tabpos(int pos, char *tab);

main(int argc, char *argv[])
{
	char tab[MAXLINE+1];
	settab(argc, argv, tab);
	detab(tab);
	return 0;
}

void detab(char *tab)
{
	int c, pos = 1;
	while ((c = getchar()) != EOF)
		if (c == '\t') {
			do
				putchar(' ');
			while (tabpos(pos++, tab) != YES);
		} else if ( c == '\n') {
			putchar(c);
			pos = 1;
		} else {
			putchar(c);
			++pos;
		}
}

