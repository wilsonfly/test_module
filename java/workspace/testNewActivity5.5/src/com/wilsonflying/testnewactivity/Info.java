package com.wilsonflying.testnewactivity;
import java.io.Serializable;

public class Info implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sex="";		//性别
	private int stature=0;	//身高
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getStature() {
		return stature;
	}
	public void setStature(int stature) {
		this.stature = stature;
	}
}
