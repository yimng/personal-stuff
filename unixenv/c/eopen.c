#include <stdio.h>

FILE *efopen(file, mode)
	char * file, *mode;
{
	FILE *fp, *fopen();
	extern char *progname;

	if ((fp = fopen(file, mode)) != NULL)
		return fp;
	fprintf(stderr, "%s: can't open file %s mode %s\n",
		progname, file, mode);
	exit(1);
}
