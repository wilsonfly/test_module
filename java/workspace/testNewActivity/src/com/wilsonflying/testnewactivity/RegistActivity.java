package com.wilsonflying.testnewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RegistActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_regist);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		TextView username = (TextView) findViewById(R.id.textview_regist_username);
		username.setText("�û�����"+bundle.getString("username"));
	}
}
