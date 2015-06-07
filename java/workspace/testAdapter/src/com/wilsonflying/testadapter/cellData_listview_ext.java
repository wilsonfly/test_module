package com.wilsonflying.testadapter;

public class cellData_listview_ext {

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

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	private String name;
	private String description;
	private int iconId;
	
	public cellData_listview_ext(String name, String des, int iconId) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.description = des;
		this.iconId = iconId;
	}
}
