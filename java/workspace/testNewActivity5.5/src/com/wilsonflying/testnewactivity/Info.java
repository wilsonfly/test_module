package com.wilsonflying.testnewactivity;
import java.io.Serializable;

public class Info implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sex="";		//�Ա�
	private int stature=0;	//���
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
