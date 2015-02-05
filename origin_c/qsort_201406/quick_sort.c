
void quick_sort(int a[],int numsize)//a是整形数组，numsize是元素个数
{
    int i=0,j=numsize-1;
    int val=a[0];//指定参考值val大小
    if(numsize>1)//确保数组长度至少为2，否则无需排序
    {
        while(i<j)//循环结束条件
        {
            if(a[j]<val)
            {
                a[i]=a[j];
                break;
            }
            for(;i<j;i++)//从前往后搜索比val大的元素，找到后填到a[j]中并跳出循环
                if(a[i]>val)
                {
                    a[j]=a[i];
                    break;
                }
        }
        a[i]=val;//将保存在val中的数放到a[i]中
        quick_sort(a,i);//递归，对前i个数排序
        quick_sort(a+i+1,numsize-1-i);//对i+1到numsize-1这numsize-1-i个数排序
    }
}
