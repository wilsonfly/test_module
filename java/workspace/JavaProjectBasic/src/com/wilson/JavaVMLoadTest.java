package com.wilson;

public class JavaVMLoadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(SubClass.value);
	}

}

class SuperClass{
	static {
		System.out.println("SuperClass init");
	}
	public static int value = 123;
}

class SubClass extends SuperClass{
	static{
		System.out.println("SubClass init");
	}
}