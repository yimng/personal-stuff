#include <stdio.h>
#define PAGESIZE 22

char *progname;

main(argc, argv)
int argc;
char *argv[];
{
	int i;
	FILE *fp, *efopen();

	progname = argv[0];
	if (argc == 1)
		print(stdin, PAGESIZE);
	else
		for (i = 1; i < argc; i++) {
			fp = efopen(argv[i], "r");
			print(fp, PAGESIZE);
			fclose(fp);
		}
	return(0);
}



