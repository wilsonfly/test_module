package com.wilsonflying.useBaseAdapter;

public class Custom {

	public String name = "";
	public String description = "";
	public int imgid = 0;

	public Custom(String name, String des, int imgid) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.description = des;
		this.imgid = imgid;
	}

	public int getImgid() {
		return imgid;
	}

	public void setImgid(int imgid) {
		this.imgid = imgid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
