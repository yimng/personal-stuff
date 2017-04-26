int strlen(char *s)
{
    int n;
    for (n = 0; *s != '\0'; s++)
        n++;
    return n;
}

int strlen2(char *s)
{
    char *p = s;
    while (*p != '\0')
        p++;
    return p - s;
}
