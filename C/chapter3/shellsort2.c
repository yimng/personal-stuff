void shellsort(int [] a, int n)
{
	int gap, i, j, tmp;
	for (gap = n / 2; gap > 0; gap /= 2)
	{
		for (i = gap; i < n; i++)
		{
			for (j = i - gap; j >= 0 && a[j] > a[j + gap]; j = j - gap)
			{
				tmp = a[j];
				a[j] = a[j + gap];
				a[j + gap] = tmp;
			}
		}
	}
}
