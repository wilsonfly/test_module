package com.wilsonflying.testhandler2;

import com.wilsonflying.testhandler2.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private ImageView imageView;
	private TextView textView;
	private Button button;
	private Button button2;
	
	Handler handler = new Handler();
	Handler handler2 = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			textView.setText("arg1:"+msg.arg1+" arg2:"+msg.arg2+" "+msg.obj);
		};
	};
	

	
	Handler handler3 = new Handler(new Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "in callback", Toast.LENGTH_SHORT).show();
			//return false;
			return true;//截获，将不能执行到下面的handleMessage
		}
	}){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "in handlemessage", Toast.LENGTH_SHORT).show();

		}
	};
	
	private int[] images={R.drawable.img001, R.drawable.img002, R.drawable.img003};
	private int index;
	private MyRunnable myRunnable = new MyRunnable();
	
	class MyRunnable implements Runnable{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			index++;
			index = index%3;
			
			imageView.setImageResource(images[index]);
			
			handler.postDelayed(myRunnable, 1000);//类似递归调用，每隔一秒调用runnable同时又定时一个。
		}
	}
	
	class Person{
		public int age;
		public String name;
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			//return super.toString();
			return "name:"+name+" age:"+age;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		textView = (TextView) findViewById(R.id.text);
		imageView = (ImageView) findViewById(R.id.image);
		
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		
		//no1
		handler.postDelayed(myRunnable, 1000);
		
		//no2
		new Thread(){
			public void run() {
				try {
					Thread.sleep(2000);
					
					//msg no1
//					Message msg = new Message();
//					msg.arg1=88;
//					msg.arg2=99;
					
					//msg no2
					Message msg = handler2.obtainMessage();
					msg.arg1=88;
					msg.arg2=99;
					
					Person person = new Person();
					person.age=20;
					person.name="MaiDou";
					
					msg.obj=person;
					
					//sendmessage no1
					//handler2.sendMessage(msg);
					
					//sendmessage no2
					msg.sendToTarget();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch ( ((Button)v).getId()) {
		case R.id.button:
			handler.removeCallbacks(myRunnable);
			break;
		case R.id.button2:
			handler3.sendEmptyMessage(1);
		default:
			break;
		}
	}
}
