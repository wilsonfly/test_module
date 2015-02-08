package com.wilson;

import java.util.ArrayList;

public class Arraylist {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		
		Clerk clerk1 = new Clerk("tom",22, 3333);
		Clerk clerk2 = new Clerk("jack",33, 3344);
		
		al.add(clerk1);
		al.add(clerk2);
		System.out.println("size:"+al.size());
		for(int i=0; i<al.size(); i++)
		{
			Clerk tmp = (Clerk)al.get(i);
			System.out.println("no:"+i+" name:"+tmp.getName());
		}
	}

}

class Clerk
{
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}

	private String name;
	private int age;
	private float sal;
	
	public Clerk(String name, int age, float sal)
	{
		this.name = name;
		this.age = age;
		this.sal = sal;
	}
}