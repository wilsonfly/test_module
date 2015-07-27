package com.wilsonflying.testintentquitapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Log.d("shs", "onNewIntent----ThirdActivity");
	}

	public void onClickBtn(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
