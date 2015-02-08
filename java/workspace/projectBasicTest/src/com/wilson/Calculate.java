package com.wilson;

public class Calculate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//运算时候都是用补码来计算的
		System.out.println("~2:" + (~2));
		System.out.println("~-5:" + (~-5));
		System.out.println("-3^3:" + (-3^3));
		
		//算术右移,符号位不变，用符号位补高位
		System.out.println("4>>1:" + (4>>1));
		System.out.println("-4>>1:" + (-4>>1));
		System.out.println("-4<<1:" + (-4<<1));
		System.out.println("-1>>2:" + (-1>>2));
		System.out.println("-1>>9:" + (-1>>9));
		
		//逻辑右移，用0补高位
		System.out.println("4>>>1:" + (4>>1));
		System.out.println("-4>>>1:" + (-4>>1));
		
		System.out.println("math.floor(-4.7):" + Math.floor(-4.7));
		System.out.println("math.ceil(-4.7):" + Math.ceil(-4.7));
		System.out.println("math.round(-4.7):" + Math.round(-4.7));

		String a = "abc";
		String b = "abc";
		String c = "ab";
		String d = "ab"+"c";

		
		if(a==b) System.out.println("equal 00");
		if(a==c) System.out.println("equal 11");
		if(a==d) System.out.println("equal 22");
		c = c+"c";
		if(a==c) System.out.println("equal 33");
		
		String aa = new String("abc");
		String bb = new String("abc");
		if(aa == bb)
			System.out.println("aa == bb");
		if(aa.equals(bb))
			System.out.println("aa equal bb");
		
	}

}
