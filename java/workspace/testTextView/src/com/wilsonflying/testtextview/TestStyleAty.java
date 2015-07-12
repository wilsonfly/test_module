package com.wilsonflying.testtextview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestStyleAty extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teststringarray);
		
		String[] arr = getResources().getStringArray(R.array.arr);
		for (int i = 0; i < arr.length; i++) {
			Log.d("shs", arr[i]);
			
		}
	}

}
