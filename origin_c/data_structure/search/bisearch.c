#include <stdio.h>

static int bisearch(int* data, int size, int key);
static void print_data(int* data, int num);

int main()
{
	int data[]={1,3,5,7,9,10,12,14,15,16,17,18,19,22,25,30,33,35};
	int key = 0, index =0;

	print_data(data,sizeof(data)/sizeof(int));
	printf("input one num from these to find it:");
	scanf("%d", &key);
	//index = bisearch_no0(data, sizeof(data)/sizeof(int), key);
	index = bisearch(data, sizeof(data)/sizeof(int), key);
	printf("index:%d\n",index);
}

static int bisearch(int* data, int size, int key)
{
	if( data==NULL || size<=0 )
		return -1;

	int front = 0, rear = size-1;
	int mid=-1;
	while( front <= rear )/*allowed to get one same position*/
	{
		mid = (front+rear)/2;
		//printf("mid:%d,front:%d,rear:%d\n",mid, front, rear);
		if(data[mid]<key)
			front = mid + 1;//front = mid;/*should calculate with 1,otherwise cannot get out when searching a noexist key*/
		else if(data[mid]>key)
			rear = mid - 1;//rear = mid;
		else
			return mid;
	}
	return -1;
}

//old version
int bisearch_no0(int *a, int n, int k)
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

static void print_data(int* data, int num)
{
	if( data==NULL || num==0 )
		return;

	int i = 0;
	for( i=0; i < num; i++ )
	{
		//printf( "%d ", data[i]);
		printf( "[%d]:%d ", i, data[i]);
	}
	printf("\n");
}
