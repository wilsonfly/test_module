package com.test;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr1 = new int[5];
		int arr2[] = new int[6];
		System.out.println("len:"+arr1.length);
		
		float arr3[] = {1, 2, 2.2f, 3.3f};
		float all = 0;
		for( int i=0; i<arr3.length; i++ )
		{
			all += arr3[i];
		}
		
		System.out.println(all);
	}

}
