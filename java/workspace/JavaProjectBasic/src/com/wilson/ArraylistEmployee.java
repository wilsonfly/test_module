package com.wilson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArraylistEmployee {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		EmpManage em = new EmpManage();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.println("choose the cmd:");
			System.out.println("1. add one emp");
			System.out.println("2. search one emp");
			System.out.println("3. modify the sal");
			System.out.println("4. del one emp");
			System.out.println("5. show all emp info");
			System.out.println("6. quit");
			
			String operat = bf.readLine();
			if(operat.equals("1"))
			{
				System.out.println("input one empNO");
				String empNo = bf.readLine();
				System.out.println("intput name:");
				String name = bf.readLine();
				System.out.println("input sal:");
				float sal = Float.parseFloat(bf.readLine());
				
				Emp emp = new Emp(empNo, name , sal);
				em.addEmp(emp);
			}
			if(operat.equals("2"))
			{
				System.out.println("input one empNO");
				String empNo = bf.readLine();
				
				em.showInfo(empNo);
			}
			if(operat.equals("3"))
			{
				System.out.println("input one empNO");
				String empNo = bf.readLine();
				System.out.println("input one sal");
				float sal = Float.parseFloat(bf.readLine());
				
				em.updateSal(empNo, sal);
			}
			if(operat.equals("4"))
			{
				System.out.println("input one empNO");
				String empNo = bf.readLine();
				em.delEmp(empNo);
			}
			if(operat.equals("5"))
			{
				em.showAll();
			}
			if(operat.equals("6"))
			{
				System.exit(0);
			}
		}

	}

}

class EmpManage
{
	private ArrayList al = null;
	
	public EmpManage()
	{
		al = new ArrayList();
	}
	
	public void addEmp(Emp emp)
	{
		al.add(emp);
	}
	
	public void showInfo(String empNo)
	{
		for(int i=0; i<al.size();i++)
		{
			Emp emp = (Emp)al.get(i);
			if(emp.getEmpNo().equals(empNo))
			{
				System.out.println("find the emp,info:");
				System.out.println("empNo:"+empNo);
				System.out.println("name:"+emp.getName());
				System.out.println("sal:"+emp.getSal());
			}
		}
	}
	
	public void updateSal(String empNo, float sal)
	{
		for(int i=0;i<al.size(); i++)
		{
			Emp emp = (Emp)al.get(i);
			if(emp.getEmpNo().equals(empNo))
			{
				emp.setSal(sal);
			}
		}
	}
	
	public void delEmp(String empNo)
	{
		for(int i=0;i<al.size(); i++)
		{
			Emp emp = (Emp)al.get(i);
			if(emp.getEmpNo().equals(empNo))
			{
				al.remove(i);
			}
		}
	}

	public void showAll()
	{
		for(int i=0;i<al.size();i++)
		{
			Emp emp = (Emp)al.get(i);
			showInfo(emp.getEmpNo());
		}
	}
}

class Emp
{
	private String empNo;
	private String name;
	private float sal;
	
	public Emp(String empNo, String name, float sal)
	{
		this.empNo = empNo;
		this.name = name;
		this.sal = sal;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}
}