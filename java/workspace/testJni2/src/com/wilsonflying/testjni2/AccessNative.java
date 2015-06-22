package com.wilsonflying.testjni2;

public class AccessNative {

	private static int age = 20;
	
	public native String readStringFromJNI();
	public native String putStringFromJNI(String str);
	public native double getSum(int x, double y);
	public native void callBackVoid();
	public native void GetMemVar();
	public native int[] updateIntArray(int[] array);
	public native int[] calculateIntArray(int[] array);

	
	public static void showToast(){
		new MainActivity().getmHandler().sendEmptyMessage(MainActivity.getSHOW_TOAST());
	}
}
