package com.wilsonflying.testrelativelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.mylayout);
		
		Button button_yes = (Button)findViewById(R.id.button1);
		button_yes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast=Toast.makeText(MainActivity.this, "�����������Ҳ�㲻��ȥ~~~", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		
		Button button_no = (Button)findViewById(R.id.button2);
		button_no.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "��֪������", Toast.LENGTH_LONG).show();
			}
		});
	}
}
