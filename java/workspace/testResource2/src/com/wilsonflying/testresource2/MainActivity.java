package com.wilsonflying.testresource2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		int[] tvID = new int[]{ R.id.str1, R.id.str2, R.id.str3, R.id.str4, R.id.str5,
				R.id.str6, R.id.str7 };
		int[] tvColor = new int[]{ R.color.color_chi, R.color.color_cheng,
				R.color.color_huang, R.color.color_lv, R.color.color_qing,
				R.color.color_lan, R.color.color_zi };
		
		for(int i=0;i<7;i++){
			TextView tv = (TextView) findViewById(tvID[i]);
			tv.setGravity(Gravity.CENTER);
			//tv.setBackgroundColor(tvColor[i]);
			tv.setBackgroundColor(getResources().getColor(tvColor[i]));
			tv.setHeight((int) getResources().getDimension(R.dimen.basic)+i*2);
		}
	}
}
