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
		
		//TODO:相应遥控器按键还有问题，目前这个Listener 完全不管用
		am.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				am.bitmapX = event.KEYCODE_X;
				am.bitmapY = event.KEYCODE_Y;
				if(event.getAction() == KeyEvent.ACTION_DOWN){
					if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT)
						am.bitmapX -= 3;
					if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
						am.bitmapX += 3;
					if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN)
						am.bitmapY += 3;
					if(keyCode == KeyEvent.KEYCODE_DPAD_UP)
						am.bitmapY -= 3;
					Log.d("huasheng", "in action_down");
				}
				Log.d("huasheng", "in action_down");

				am.invalidate();
				return true;
			}
		});
		
		framelayout.addView(am);
	}
}
