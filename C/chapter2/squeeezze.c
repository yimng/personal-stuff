#include<stdio.h>

void squeeezze(char s1[], char s2[])
{
	int i, j, k;
	
	for (i = k = 0; s1[i] != '\0'; i++) {
		for (j = 0; s2[j] != '\0' && s2[j] != s1[i]; j++)
			;
		if (s2[j] == '\0')
			s1[k++] = s1[i];
	}
	s1[k] = '\0';
}

int main()
{
  char s1 [] = "abcdddefghijklmn";
  char s2 [] = "cdin888";
  squeeezze(s1, s2);
  printf("%s\n", s1);
}
