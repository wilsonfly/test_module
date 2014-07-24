#include <stdio.h>
#include <stdlib.h>

int bisearch(int *a, int n, int k)
{
	int low, mid, high;
	low = 0;
	high = n-1;
	mid = (low + high) / 2;
	while(low <= high)
	{
		if(k < a[mid])
			high = mid - 1;
		else if(k > a[mid])
			low = mid + 1;
		else
			return mid;

		mid = (low + high) / 2;
	}
	return -1;
}

int main()
{
	int a[] = {1, 3, 5, 7, 9};
	int ret, k;
	while(scanf("%d", &k) == 1)
	{
		ret = bisearch(a, sizeof(a) / sizeof(int), k);
		printf("%d\n", ret);
	}

	return 0;
}
