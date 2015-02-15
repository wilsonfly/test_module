package com.wilsonflying.testnewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegsitActivityBack extends Activity {
	private final int REQUESTCODE = 100;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_regsit_back);
		
		final Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		TextView tv = (TextView) findViewById(R.id.textview_regist_back_username);
		tv.setText("注册的用户名："+bundle.getString("username").toString());
		
		Button button = (Button) findViewById(R.id.button_backToMain);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(REQUESTCODE, intent);
				finish();
			}
		});
	}
}
