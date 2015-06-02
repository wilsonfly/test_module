package com.wilson;

import java.lang.reflect.Method;

public class GenericJava {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Gen<Bird> gen1 = new Gen<Bird>(new Bird());
		gen1.showTypeName();
	}

}

class Gen<T>
{
	private T o;
	
	public Gen(T a)
	{
		o = a;
	}
	
	public void showTypeName()
	{
		System.out.println("the type:");
		Method []m = o.getClass().getDeclaredMethods();
		
		for(int i=0;i<m.length;i++)
		{
			System.out.println(m[i].getName());
		}
	}
}

class Bird
{
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private String color;
	private int age;
}

