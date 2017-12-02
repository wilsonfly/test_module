package com.wilson;

public class JavaVMLoadTest3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ConstClass.HELLO);
	}

}

class ConstClass{
	static {
		System.out.println("ConstClass init");
	}
	public static final String HELLO = "hello world!";
}

