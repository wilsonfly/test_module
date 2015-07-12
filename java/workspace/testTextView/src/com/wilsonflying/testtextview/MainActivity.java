package com.wilsonflying.testtextview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	public void onClickBtn(View view){
	switch (view.getId()) {
	case R.id.button1:
		startActivity(new Intent(MainActivity.this, TextViewNo1Aty.class));
		break;
	case R.id.button2:
		startActivity(new Intent(MainActivity.this, TextViewNo2Aty.class));
		break;
	case R.id.button3:
		startActivity(new Intent(MainActivity.this, TestStringArrayAty.class));
		break;
	case R.id.button4:
		startActivity(new Intent(MainActivity.this, TestStyleAty.class));
		break;

	default:
		break;
	}	
	}

}
