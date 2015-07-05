package com.wilsonflying.testfragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailAty extends Activity{
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_detail);
		
		tv = (TextView) findViewById(R.id.tv_detail);
		tv.setText(getIntent().getExtras().getString("detailInfo"));
	}
}
