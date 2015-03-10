package com.wilson;

import java.util.Calendar;

public class Sort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int arr[] = {0,-2,5,1,3,9,33,44,22,99,3,-3};
		int arr[] = {331, 590, 200, 975, 889, 116, 240, 906, 949, 755}; 
/*
		int len=10;
		int arr[] = new int[len];
		for(int i=0;i<len;i++)
		{
			//生成1-1000随机数
			arr[i]=(int)(Math.random()*1000);
		}
		*/
		System.out.println("origin arr:");
		show_arr(arr);
		
		Select st = new Select();
		//st.sort(arr);
		
		Bubble bb = new Bubble();
		//bb.sort(arr);
		
		InsetSort is = new InsetSort();
		//is.sort(arr);
		
		InsertSort_no2 is2 = new InsertSort_no2();
		//is2.sort(arr);
		
		QuickSort qs = new QuickSort();
		//qs.sort(0, arr.length-1, arr);
		
		/*
		Calendar cal = Calendar.getInstance();
		System.out.println("before:"+cal.getTime());
		//bb.sort(arr);
		st.sort(arr);
		cal = Calendar.getInstance();
		System.out.println("after:"+cal.getTime());
		 */
		System.out.println("final arr:");
		show_arr(arr);
	}

	static void show_arr(int arr[])
	{
		for( int i=0; i<arr.length;i++ )
		{
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

}

class Bubble{
	public void sort(int arr[])
	{
		int tmp=0;
		for( int i=0;i<arr.length-1;i++ )
		{
			//与每个相遇的数据比较，沿途挑选大者往后交换直到放到最后位置
			for( int j=0; j<arr.length-1-i;j++ )
			{
				if( arr[j]>arr[j+1] )
				{
					tmp = arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=tmp;
				}
			}

		}
	}
}

class Select{
	public void sort(int arr[]){
		int min,p=0;
		int tmp=0;
		for(int i=0;i<arr.length;i++ )
		{
			min=arr[i];
			p=i;
			for(int j=i;j<arr.length;j++)
			{
				if(arr[j]<min)
				{
					min=arr[j];
					p=j;
				}
			}
			//每一次循环挑选出最小值与最前边起始位置 交换
			tmp = arr[i];
			arr[i]=arr[p];
			arr[p]=tmp;
		}
	}
}

class InsetSort{
	public void sort(int arr[])
	{
		// not a good algorithm
		int tmp = 0;
		int j = 0;
		for(int i=1;i<arr.length;i++)
		{
			tmp = arr[i];
			j = i;
			for( ;j>0;j--)
			{
				if(arr[j-1]>tmp)
				{
					arr[j]=arr[j-1];
				}
				else
					break;
			}
			arr[j] = tmp;
		}
	}
}

class InsertSort_no2
{
	public void sort(int arr[])
	{
		for(int i=0;i<arr.length;i++)
		{
			//把前面 的数据看做一组已经有序的数据，将数据插入到前面有序数据中，一次插入其中直到最后一个数据
			int j=i;
			int tmp=arr[i];
			while(j>0 && arr[j-1]>tmp)
			{
				arr[j]=arr[j-1];
				j--;
			}
			arr[j]=tmp;
		}
	}

}

class QuickSort
{
	public void sort(int left, int right, int arr[])
	{
		int i = left, j = right;
		int key=arr[i];
		int tmp = 0;

		while(i<j)
		{
			while(i<j && key<=arr[j])
			{
				j--;
			}
			arr[i]=arr[j];
			arr[j] = key;
			key = arr[i];
			//System.out.println("i:"+i+" j:"+j+" key:"+key);
			//Sort.show_arr(arr);
			
			while(i<j && arr[i]<=arr[j])
			{
				i++;
			}
			tmp = arr[i];
			arr[i]=arr[j];
			arr[j] = tmp;
			key = arr[i];
			//System.out.println("i:"+i+" j:"+j+" key:"+key);
			//Sort.show_arr(arr);
		}
		System.out.println("one circle,result:");
		Sort.show_arr(arr);
		
		if(i==left && j==right)
			return;
		else
			sort(left,right,arr);
				
	}
}