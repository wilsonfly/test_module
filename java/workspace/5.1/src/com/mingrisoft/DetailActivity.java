package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);		//���ò����ļ�
		Button button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();	//�رյ�ǰActivity
				
			}
		});
	}

}
