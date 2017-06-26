int stridex(char *s , char *t)
{
    char *i, *j, *k;
    for (i = s; *i != '\0'; i++) {
        for (j = i, k = t; *k != '\0' && *j == *k; j++, k++)
            ;
        if (k - t > 0 && *k == '\0')
            return i - s;
    }
    return -1;
}
