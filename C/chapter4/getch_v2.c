#include <stdio.c>

char buf = 0;

int getchar()
{
    int c;
    if (buf == 0)
        c = getchar();
    else
        c = buf;
    buf = 0;
    return c;
}

void ungetch(int c)
{
    if (buf != 0)
        printf("ungetch: too many characters\n");
    else
        buf = c;
}
