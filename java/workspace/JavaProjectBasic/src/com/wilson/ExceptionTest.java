package com.wilson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

public class ExceptionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			FileReader fr = new FileReader("a.txt");
			//有异常则进入到catch中，不再继续执行下面代码
			System.out.println("after filereader,call socket");
			Socket s = new Socket("172.16.6.117",2222);
		//} catch (Exception e) {
		}catch (FileNotFoundException e){
			// TODO: handle exception
			System.out.println("message:"+e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}finally{
			System.out.println("in the block finally");
		}
		
		System.out.println("after the exception");
		
		
		function(4);
	}
	
	public static int function(int n)
	{
		System.out.println("function("+n+"):");
		Throwable t = new Throwable();
		StackTraceElement[] st = t.getStackTrace();
		for(StackTraceElement f:st)
		{
			System.out.println(f);
		}
		
		int r;
		if(n <= 1)
			r = 1;
		else
			r = n * function(n-1);
		
		System.out.println("return:"+r);
		return r;
	}

}
