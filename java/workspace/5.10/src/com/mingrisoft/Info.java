package com.mingrisoft;
import java.io.Serializable;

public class Info implements Serializable {

	private static final long serialVersionUID = 1L;
	private String birthday="";		//ÉúÈÕ
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
