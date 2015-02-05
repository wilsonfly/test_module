#include <stdio.h>
#include "llog_priv.h"

int arr[] = {331, 590, 200, 975, 889, 116, 240, 906, 949, 755};
void show_arr(int arr[]);

int main()
{
    quick_sort(arr, sizeof(arr)/sizeof(int));
    show_arr(arr);

}

void show_arr(int arr[])
{
    int i = 0;
    for( ; i<sizeof(arr)/sizeof(int); i++ )
    {
        printf("%d,", arr[i]);
    }
    printf("\n");
}

