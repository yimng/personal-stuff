#include <stdio.h>
int getline(char * line, int max)
{
    int c;
    char *p = line;
    while ((c = getchar()) != EOF && && c != '\n' && --max > 0) {
        *p++ = c;
    }
    if (c == '\n')
        *p++ = c;
    *p = '\n';
    return p - line;
}
