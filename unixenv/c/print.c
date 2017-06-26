#include <stdio.h>
print(fp, pagesize)
	FILE *fp;
	int pagesize;
{
	static int lines = 0;
	char buf[BUFSIZ];

	while (fgets(buf, sizeof buf, fp) != NULL)
		if (++lines < pagesize)
			fputs(buf, stdout);
		else {
			buf[strlen(buf) - 1] = '\0';
			fputs(buf, stdout);
			fflush(stdout);
			ttyin();
			lines = 0;
		}
}
