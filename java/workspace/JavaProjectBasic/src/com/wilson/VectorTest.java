package com.wilson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class VectorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector vv = new Vector();
		
		Emp emp1 = new Emp("001", "xiaoxue", 22f);
		Emp emp2 = new Emp("002", "xiaoxueeee", 22f);
		Emp emp3 = new Emp("003", "xueeee", 22f);
		
		vv.add(emp1);
		vv.add(emp2);
		vv.add(emp3);
		
		for(int i=0; i<vv.size();i++)
		{
			System.out.println( ((Emp)vv.get(i)).getName());
		}
		
		
		HashMap hm = new HashMap();
		
		hm.put("key01", emp1);
		hm.put("key02", emp2);
		hm.put("key02", emp3);//覆盖前一个相同的key的内容
		
		if(hm.containsKey("key02"))
		{
			System.out.println("has this key");
			Emp emp = (Emp)hm.get("key02");
			System.out.println("name:"+emp.getName());
		}
		else
		{
			System.out.println("not has this key");
		}
		
		Iterator it = hm.keySet().iterator();
		while(it.hasNext())
		{
			String key = it.next().toString();
			//无序
			Emp emp = (Emp)hm.get(key);
			System.out.println("get name:"+emp.getName());
		}
		
		//hashmap 可以放null值，hashtable则不可以
		HashMap hm2 = new HashMap();
		hm2.put(null, null);
		System.out.println("get hashmap:"+hm2.get(null));
	}

}
