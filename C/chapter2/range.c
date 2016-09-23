#include <limits.h>
#include <float.h>

int main()
{
	printf("signed char min 	= %d\n", -(char)((unsigned char)~0 >> 1));
	printf("signed char max		= %d\n", (char)((unsigned char)~0 >> 1));
	printf("signed short min	= %d\n", -(short)((unsigned short)~0 >> 1));
	printf("signed short max	= %d\n", (short)((unsigned short)~0 >> 1));
	printf("signed int min		= %d\n", -(int)((unsigned int)~0 >> 1));
	printf("signed int max		= %d\n", (int)((unsigned int)~0 >> 1));
	printf("signed long min		= %ld\n", -(long)((unsigned long)~0 >> 1));
	printf("signed long max		= %ld\n", (long)((unsigned long)~0 >> 1));

	printf("unsigned char		= %u\n", (unsigned char)~0);
	printf("unsigned short		= %u\n", (unsigned short)~0);
	printf("unsigned int		= %u\n", (unsigned int)~0);
	printf("unsigned long		= %lu\n",(unsigned long)~0);
	
}
