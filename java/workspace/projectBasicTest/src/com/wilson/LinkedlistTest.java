package com.wilson;

import java.util.*;

public class LinkedlistTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList ll = new LinkedList();
		Emp emp = new Emp("sn001", "xiaopang", 22f);
		Emp emp2 = new Emp("sn002", "xiaoxue", 23f);
		
		ll.addFirst(emp);
		ll.addFirst(emp2);
		
		for(int i=0;i<ll.size();i++)
		{
			System.out.println( ((Emp)ll.get(i)).getName());
		}
	}

}
