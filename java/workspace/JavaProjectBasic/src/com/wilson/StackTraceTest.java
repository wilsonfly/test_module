package com.wilson;

import java.util.Scanner;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

public class StackTraceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter num:");
		int n = in.nextInt();
		factorial(n);
		Logger logger = Logger.getLogger("com.wilson", StackTraceTest.class);
		
		//something strange, should have log(String className, String methodName, Object param); 
		logger.log(Level.WARNING, "some msg");
		logger.entering("entering");
		logger.exiting("exiting");
	}

	private static int factorial(int n) {
		// TODO Auto-generated method stub
		System.out.println("factorial(" + n + ")");
		Throwable t = new Throwable();
		StackTraceElement[] frames = t.getStackTrace();
		for (StackTraceElement f : frames) {
			System.out.println(f);
		}

		int r;
		if (n <= 1)
			r = 1;
		else
			r = n * factorial(n - 1);
		System.out.println("return " + r);
		return r;
	}

}
