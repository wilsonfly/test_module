package com.wilsonflying.testviewpager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Welcome extends Activity {

	private final int DELAY_TIME = 2000;
	private final int GO_HOME = 1000;
	private final int GO_GUIDE = 1001;
	private final String LAUNCH_KEY = "isFirstTimeLaunched";

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case GO_HOME:
				Intent intent = new Intent(Welcome.this, MainActivity.class);
				startActivity(intent);
//				try {
////					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				finish();
				break;
				
			case GO_GUIDE:
				Intent intent2 = new Intent(Welcome.this, Guide.class);
				startActivity(intent2);
				finish();
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_welcome);
		
		init();
	}
	
	public void init(){

		SharedPreferences pre = getSharedPreferences(LAUNCH_KEY, MODE_PRIVATE);
		boolean isFirstLaunch = pre.getBoolean(LAUNCH_KEY, true);
		if(isFirstLaunch){
//			handler.sendEmptyMessageAtTime(GO_GUIDE, DELAY_TIME);
			handler.sendEmptyMessageDelayed(GO_GUIDE, DELAY_TIME);//延时1秒发送消息来启动新activity更新ui
			Editor editor = pre.edit();
			editor.putBoolean(LAUNCH_KEY, false);
			editor.commit();
		}else{
			handler.sendEmptyMessageDelayed(GO_HOME, DELAY_TIME);
		}
	}
	
}
