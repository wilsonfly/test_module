package com.wilson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testSplit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello...");
		Pattern pattern = Pattern.compile("[,|]");
		String[] result = pattern.split("Java Hello World  Java,Hello,,World|Sun");
		for(int i=0; i<result.length; i++){
			System.out.println(result[i]);
		}
		
		// 查找以 Java 开头 , 任意结尾的字符串 
		  Pattern pattern2 = Pattern.compile("^Java.*");
		  Matcher matcher = pattern2.matcher("Java 不是人 ");
		  boolean b= matcher.matches();
		  // 当条件满足时，将返回 true ，否则返回 false
		  System.out.println("result:"+b);
		
	}

}
