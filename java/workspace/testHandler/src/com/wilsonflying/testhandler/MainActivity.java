package com.wilsonflying.testhandler;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable{

	private Handler handler;
	private String[] text = new String[]{"Text No1", "Text No2", "Text No3"};
	private int[] path = new int[]{R.drawable.img001, R.drawable.img002, R.drawable.img003};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		
		Thread thread = new Thread(this);
		thread.start();
		
		handler = new Handler(){
			
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				
				TextView tv = (TextView) findViewById(R.id.text);
				ImageView iv = (ImageView) findViewById(R.id.image);
				
				if(msg.what == 0x110){
					tv.setText(msg.getData().getString("title"));
					iv.setImageResource(path[msg.arg1]);
				}

				super.handleMessage(msg);
			}
		};
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int index;
		while(!Thread.currentThread().isInterrupted()){
			index = new Random().nextInt(path.length);
			
			//Message m = Message.obtain();
			Message m = handler.obtainMessage();
			m.what = 0x110;
			m.arg1 = index;
			
			Bundle bundle = new Bundle();
			bundle.putString("title", text[index]);
			m.setData(bundle);
			
			handler.sendMessage(m);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
