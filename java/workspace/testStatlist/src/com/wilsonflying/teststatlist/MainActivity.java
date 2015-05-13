package com.wilsonflying.teststatlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

//button_stat 中android:stat_xx 还有很多场景

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		final Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button button = (Button) v;
				button.setEnabled(false);
				button.setText("现在不能用了");
				Toast.makeText(MainActivity.this, "暂时失效", Toast.LENGTH_SHORT).show();
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				button1.setEnabled(true);
				button1.setText("可用button");
			}
		});
	}
}
