package com.wilsonflying.DefineMyView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
	}
	
	public void onClickBtn(View view){
		switch (view.getId()) {
		case R.id.button1:
			startActivity(new Intent(MainActivity.this, ShowMyViewAty.class));
			break;
		case R.id.button2:
			startActivity(new Intent(MainActivity.this, ShowMyRotatingViewAty.class));
			break;
		default:
			break;
		}
	}
}
