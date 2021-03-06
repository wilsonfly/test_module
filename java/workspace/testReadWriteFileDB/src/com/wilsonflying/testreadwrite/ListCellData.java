package com.wilsonflying.testreadwrite;

import android.content.Context;
import android.content.Intent;

public class ListCellData {

	public ListCellData(Context context, String controlName, Intent targetIntent) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.controlString = controlName;
		this.targetIntent = targetIntent;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getControlString() {
		return controlString;
	}

	public void setControlString(String controlString) {
		this.controlString = controlString;
	}

	public Intent getTargetIntent() {
		return targetIntent;
	}

	public void setTargetIntent(Intent targetIntent) {
		this.targetIntent = targetIntent;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
//		return super.toString();
		return controlString;
	}

	private Context context;
	private String controlString;
	private Intent targetIntent;
}
