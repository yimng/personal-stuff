#include <stdio.h>

int getch(void);
void ungetch(int);

int getint(float *pn)
{
    int c, sign;
    float power;

    while (isspace(c = getch()))
        ;
    if (!isdigit(c) && c != EOF && c != '+' && c != '-') {
        ungetch(c);
        return 0;
    }
    sign = (c == '-') ? -1 : 1;
    if (c == '+' || c == '-') {
        c = getch();
    }
    for (*pn = 0.0; isdigit(c); c = getch()) {
        *pn = 10.0 * *pn + (c - '0');
    }
    if (c  == '.')
        c = getch();
    for (power = 1.0; isdigit(c); c = getch()) {
        power *= 10.0;
        *pn = 10.0 * *pn +(c - '0');
    }

    *pn *= sign / power;
    if (c != EOF)
        ungetch(c);
    return c;
}
