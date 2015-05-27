package com.wilsonflying.testframelayout;

import com.wilsonflying.testframelayout.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	
	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.mylayout);
		
		FrameLayout framelayout = (FrameLayout)findViewById(R.id.mylayout);
		final DRAmengView am = new DRAmengView(MainActivity.this);

		
		am.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				am.bitmapX = event.getX();
				am.bitmapY = event.getY();
				am.invalidate();
				return true;
			}
		});
		
	
		
		framelayout.addView(am);
	}
}
