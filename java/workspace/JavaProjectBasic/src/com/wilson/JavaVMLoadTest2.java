package com.wilson;

public class JavaVMLoadTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SuperClass2[] sc = new SuperClass2[10];
		System.out.println("main");
	}

}

class SuperClass2{
	static {
		System.out.println("SuperClass2 init");
	}
	public static int value = 123;
}

class SubClass2 extends SuperClass2{
	static{
		System.out.println("SubClass2 init");
	}
}