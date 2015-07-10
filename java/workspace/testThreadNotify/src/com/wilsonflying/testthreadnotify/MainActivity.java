package com.wilsonflying.testthreadnotify;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

//	private boolean flag = false;//boolean is not a valid type's argument for the synchronized statement
	private Token mToken;
	private final String TAG = "testThreadNotify";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mToken = new Token("something");
	}
	
	public void onClickStartThreads(View view){
		new NotifyThread("notify_thread").start();
		new WaitThread("wait_thread_no1").start();
		new WaitThread("wait_thread_no2").start();
		new WaitThread("wait_thread_no3").start();

	}
	
	public class Token{
		private String content;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Token(String content) {
			// TODO Auto-generated constructor stub
			this.content = content;
		}
	}

	public class WaitThread extends Thread {
		public WaitThread(String name) {
			// TODO Auto-generated constructor stub
			super(name);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub


			synchronized (mToken) {
				long lastTime = System.currentTimeMillis();
				try {
					Log.d(TAG, getName()+" before wait content:"+mToken.getContent());
					mToken.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Log.d(TAG, getName()+" wait time:"+(System.currentTimeMillis() - lastTime));
			}
			
			Log.d(TAG, getName()+" after wait, content:"+mToken.getContent());
		}

	}

	public class NotifyThread extends Thread {
		public NotifyThread(String name) {
			// TODO Auto-generated constructor stub
			super(name);
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Log.d(TAG, getName()+" before sleep content:"+mToken.getContent());
				sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized (mToken) {
				mToken.setContent("something new ...");
//				mToken.notify();
				mToken.notifyAll();
				Log.d(TAG, getName()+" after sleep content:"+mToken.getContent());
			}
		}
	}

}
