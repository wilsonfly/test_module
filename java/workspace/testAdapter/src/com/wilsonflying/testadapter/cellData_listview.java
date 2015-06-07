package com.wilsonflying.testadapter;

public class cellData_listview {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private String name;
	private String sex;
	private int age;

	public cellData_listview(String name, String sex, int age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
}
