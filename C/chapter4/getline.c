#include <stdio.h>
int getlin(char s[], int limit)
{
    int c, i;
    i = 0;
    while (--limit > 0 && (c = getchar()) != EOF && c != '\n')
    {
        s[i++] = c;
    }
    if (c == '\n') {
        s[i++] = c;
    }
    s[i] = '\0';
    return i;
}
