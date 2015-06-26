package com.wilsonflying.testhttp;

import android.app.ListActivity;
import android.content.Intent;

public class ListCellData extends ListActivity {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	private String name;
	private Intent intent;
	
	public ListCellData(String name, Intent intent) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.intent = intent;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
}
