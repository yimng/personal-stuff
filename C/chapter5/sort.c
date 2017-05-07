#include <stdio.h>
#include <string.h>

#define NUMERIC		1
#define DECR		2
#define LINES		100

int charcmp(char *, char *);
int numcmp(char *, char *)
int readlines(char *lineptr[], int maxlines);
void qsort(char *v[], int left, int right,
		int (*comp)(void *, void *));
void writelines(char *lineptr[], int nlines, int decr);

static char option = 0;

main(int argc, char *argv[])
{
	char *lineptr[LINES];
	int nlines;
	int c, rc = 0;
	
	while (--argc > 0 && (*++argv)[0] == '-')
		while (c = *++argv[0])
			switch (c) {
			case 'f':
				option |= FOLD;
				break;
			case 'n':
				option |= NUMERIC;
				break;
			case 'r':
				option |= DECR;
				break;
			default:
				printf("sort: illegal option %c\n", c)
				argc = 1;
				rc = -1;
				break;
			}
	if (argc)
		printf("Usage: sort -nr \n");
	else 
		if ((nlines = readlines(lineptr, LINES)) > 0) {
			if (option & NUMERRIC)
				qsort((void **) lineptr, 0, nlines - 1, (int (*) (void *, void *)) numcmp);
			else if (option & FOLD)
				qsort((void **) lineptr, 0, nlines - 1, (int (*) (void *, void *)) charcmp);
			else
				qsort((void **) lineptr, 0, nlines - 1, (int (*) (void *, void *)) strcmp);
			writelines(lineptr, nlines, option & DECR);
		} else {
			printf("input too big to sort \n");
			rc = -1;
		}
	return rc;
}

void writelines(char *lineptr[], int nlines, int decr)
{
	int i;
	if (decr)
		for (i = nlines - 1; i >= 0; i--)
			printf("%s\n", lineptr[i]);
	else
		for (i = 0; i < nlines; i++)
			printf("%s\n", lineptr[i]);
}
int charcmp(char *s, char *t)
{
	for (; tolower(*s) == tolower(*t); s++, t++)
		if (*s == '\0')
			return 0;
	return tolower(*s) - tolower(*t);
}
