package com.wilsonflying.caller;

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
		
//		Intent intent = new Intent("actionTestInstallApk");
		
		Intent intent = new Intent();
		intent.setAction("actionTestInstallApk");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		
	}
}
