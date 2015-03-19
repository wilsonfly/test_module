package com.wilsonflying.testhandleevent;

import com.wilsonflying.testhandleevent.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.layout_main);
		
		Button button_longclick = (Button) findViewById(R.id.button_longclick);
		button_longclick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(this, "short click",Toast.LENGTH_SHORT).show();
				Toast.makeText(MainActivity.this, "short click", Toast.LENGTH_SHORT).show();
			}
		});
		
		button_longclick.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "long click", Toast.LENGTH_SHORT).show();

				return true;
				//return false;
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Toast.makeText(this, "按返回键已经被截掉了，返回不了喽", Toast.LENGTH_SHORT).show();
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}

}
