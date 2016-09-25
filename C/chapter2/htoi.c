#define YES	1
#define NO	0
int htoi(char s[])
{

	int i = 0;
	int hexdigit, inhex, n;
	n = 0;
	inhex = YES;
	if (s[i] == '0') {
		i++;
		if (s[i] == 'x' || s[i] == 'X') {
			i++;
		}
	}
	for (; inhex == YES; i++) {
		if (s[i] >= '0' && s[i] <= '9') {
			hexdigit = s[i] - '0';
		} else if (s[i] >= 'a' && s[i] <= 'f') {
			hexdigit = 10 + s[i] - 'a';
		} else if (s[i] >= 'A' && s[i] <= 'F') {
			hexdigit = 10 + s[i] - 'A';
		} else {
			inhex = NO;
		}
		n = 16 * n + hexdigit;
	}
	return n;
}
