package com.wilsonflying.testListActivity;

import android.content.Context;
import android.content.Intent;

public class ListCellData {

	public ListCellData(Context context, String controlName, Intent targetIntent) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.controlName = controlName;
		this.targetIntent = targetIntent;
	}
	
	private Context context;
	public Context getContext() {
		return context;
	}
	
	private String controlName;
	public String getControlName() {
		return controlName;
	}
	
	private Intent targetIntent;
	public Intent getTargetIntent() {
		return targetIntent;
	}	
	
	public void startActivity(){
		getContext().startActivity(getTargetIntent());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
//		return super.toString();
		return getControlName();
	}
}
