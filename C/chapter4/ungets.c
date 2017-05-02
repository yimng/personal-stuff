#include <string.h>

void ungets(char s[])
{
    int len = strlen(s);
    void ungetch(int c);
    while (len > 0) {
        ungetch(s[--len]);
    }
}
