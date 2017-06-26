#define abs(x) ((x) < 0 ? -(x) : (x))

/* itoa: convert n to characters in s - modified */

void itos(int n, char s[])
{
    int i, sign;
    void reverse(char s[]);

    sign = n;   /* record sign */
    i = 0;
    do {
        s[i++] = abs(n % 10) + '0';
    } while ((n /= 10) != 0);
    if (sign < 0)
        s[i++] = '-';
    s[i] = '\0';
    reverse(s);

}
