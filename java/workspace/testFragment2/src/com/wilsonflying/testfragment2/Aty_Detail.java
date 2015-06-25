package com.wilsonflying.testfragment2;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class Aty_Detail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			finish();
			return;
		}
		
		if(savedInstanceState == null){
			DetailFragment fg = new DetailFragment();
			fg.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(android.R.id.content, fg).commit();//content是什么？
		}
	}
}
