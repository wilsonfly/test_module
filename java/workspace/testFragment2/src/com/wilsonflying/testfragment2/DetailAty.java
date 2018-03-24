package com.wilsonflying.testfragment2;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class DetailAty extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("DetailAty" , "onCreated");
		
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			Log.i("DetailAty" , "will finish");
			finish();//竖屏显示此activity的时候旋转屏幕，会走到这里
			return;
		}
		
		if(savedInstanceState == null){
			Log.i("DetailAty" , "will new DetailFragment");
			DetailFragment fg = new DetailFragment();
			fg.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(android.R.id.content, fg).commit();//content是什么？此activity显示的内容是怎么得来的？
		}
	}
}
