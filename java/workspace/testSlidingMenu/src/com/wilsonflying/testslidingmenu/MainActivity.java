package com.wilsonflying.testslidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {
	private SlidingMenu mSlidingMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSlidingMenu = new SlidingMenu(this);
		mSlidingMenu.setMode(SlidingMenu.LEFT);
//		mSlidingMenu.setBehindOffset(R.dimen.sliding_menu_offset);//留意配置的事offset数值
		mSlidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);//留意配置的是offset资源id
		mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		mSlidingMenu.setMenu(R.layout.layout_slidingmenu);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			Log.d("shs", "in menu");
			mSlidingMenu.toggle(true);
			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void onClickBtn(View view){
		switch (view.getId()) {
		case R.id.btnSlidingNo1:
			Toast.makeText(MainActivity.this, "点击了button1", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
