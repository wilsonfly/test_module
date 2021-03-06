package com.wilsonflying.testannotation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

@EActivity(R.layout.activity_third)
public class ThirdAty extends Activity {

	@Extra(MainActivity.NAME_KEY)
	String name;
	@Extra(MainActivity.AGE_KEY)
	int age;
	
	@ViewById
	TextView tvName;
	@ViewById
	TextView tvAge;
	
	@AfterViews
	public void initView(){
		tvName.setText(name);
		tvAge.setText(String.valueOf(age));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
}
