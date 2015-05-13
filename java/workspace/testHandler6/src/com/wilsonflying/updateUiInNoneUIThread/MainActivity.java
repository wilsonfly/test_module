package com.wilsonflying.updateUiInNoneUIThread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView) findViewById(R.id.text);
		
		new Thread(){
			
			public void run() {
				
				Handler handler = new Handler();
				
				tv.setText("updae text in NoneUiThread!!!");//will succeed!
				//activity 有个viewRootImpl此时还没有创建出来，是在onResume中创建出来，而又是在onCreate之后被调用。
				//也就没有办法检测当前线程是否跟ui线程是否是同一个。
				//vieRootImpl是负责处理更新ui的判断处理逻辑，其初始化在activity的onResume中。
				//主要涉及viewRootImpl.java : checkThread  HandlerResumActivity.java
				
//				try {
//					Thread.sleep(2000);
//					tv.setText("this time will fail to updae text in NoneUiThread After sleep 2s");
//					
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			};
		}.start();
	}


}
