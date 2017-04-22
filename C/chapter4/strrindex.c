#include <string.h>
int strrindex(char s[], char t[]) {
    int i, j, k;
    for (i = strlen(s) - strlen(t); i >= 0; i--) {
        for (j = i, k = 0; s[j] == t[k] && t[k] != '\0'; j++, k++) {
            ;
        }
        if (k > 0 && t[k] == '\0')
            return i;
    }
    return -1;
}
