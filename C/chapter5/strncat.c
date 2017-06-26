void strncat(char *s, char *t, int n)
{
    void strncpy(char *s, char *t, int n);
    int strlen(char *s);
    strncpy(s + strlen(s), t, n);
}
