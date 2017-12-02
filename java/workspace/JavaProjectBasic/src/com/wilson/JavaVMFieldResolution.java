package com.wilson;

public class JavaVMFieldResolution {

	interface Interface0{
		int value = 0;
	}
	
	interface Interface1 extends Interface0{
		int value = 1;
	}
	
	interface Interface2 {
		int value = 2;
	}
	
	static class Parent implements Interface1{
		public static int value = 3;
	}
	
	static class Sub extends Parent implements Interface2{
		public static int value = 4;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Sub.value);
	}

}
