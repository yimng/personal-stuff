void quick_sort(int a [], int left, int right)
{
  if (left > right)
    return;
  int l = left;
  int r = right;
  int pivot = a[left];
  while (l < r) {
    while (l < r && a[r] >= pivot)
    {
      j--;
    }
    while (l < r && a[l] <= pivot)
    {
      l++;
    }
    if (l < r) {
      int temp = a[l];
      a[l] = a[r];
      a[r] = temp;
    }
  }
  a[left] = a[l]
  a[l] = pivot;
  quick_sort(a, left, l - 1);
  quick_sort(a, l + 1, right);
}

