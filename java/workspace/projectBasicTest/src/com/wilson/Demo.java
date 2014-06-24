package com.wilson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Dog dogs[] = new Dog[4];
		/*
		dogs[0] = new Dog();
		dogs[0].setName("dog_1");
		dogs[0].setWeight(20);
		*/
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		for( int i=0; i<4; i++ )
		{
			dogs[i] = new Dog();
			System.out.println("input dog No."+ i + " name ");
			try {
				String name = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("input No."+ i + " dog weight");
			String s_weight = br.readLine();
			float weight = Float.parseFloat(s_weight);
			dogs[i].setWeight(weight);
					
		}
		
		float allw = 0;
		for( int i=0;i<4;i++ )
		{
			allw += dogs[i].getWeight();
		}
		System.out.print("all weight:"+allw);
	}

}

class Dog{

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	private String name;
	private float weight;
	
}

class SomeTest
{
	public void aMethod()
	{
		int i = 1;
		int j = 2;
		boolean a = true;
		boolean b = true;
		//布尔值之间可以进行与或操作
		if( i==1 || j==2 )
		{
			System.out.println("hello");
		}
		
		//布尔值之间可以进行按位与或操作
		if( i==1 & j==2 )
		{
		}
		/*
		if( i )
		{
		}*/
		
		if( a==b )
		{
		}
	}

}