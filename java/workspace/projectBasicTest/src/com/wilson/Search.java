package com.wilson;

public class Search {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {2, 5, 7, 11, 20, 35};
		int val = 20;
		BinaySearch bs = new BinaySearch();
		bs.Search(0, arr.length-1, val, arr);

	}

}


class BinaySearch
{
	public void Search(int left, int right, int val, int arr[])
	{
		int mid = (left+right)/2;
		int midVal = arr[mid];
		
		if(left<=right)
		{
			if(midVal > val)
			{
				Search(left, mid-1, val, arr);
			}
			else if(midVal < val)
			{
				Search(mid+1, right, val, arr);
			}
			else
			{
				System.out.println("find the val:"+val+" index:"+mid);
			}
		}
	}
}