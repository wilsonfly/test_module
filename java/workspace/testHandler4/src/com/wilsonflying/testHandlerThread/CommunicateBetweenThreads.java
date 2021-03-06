package com.wilsonflying.testHandlerThread;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CommunicateBetweenThreads extends Activity implements OnClickListener{

	private Button button_send;
	private Button button_stop;
	private final int TO_MASTER = 100;
	private final int TO_SLAVE = 101;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			if(msg.what == TO_MASTER){
				System.out.println("in main handler,msg.arg1:"+msg.arg1);
				Toast.makeText(getApplicationContext(), "in main handler,msg.arg1:"+msg.arg1, Toast.LENGTH_SHORT).show();
			
				Message message = Message.obtain();
				message.what = TO_SLAVE;
				message.arg1 = 66;
				threadHandler.sendMessageDelayed(message, 4000);
			}
		};
	};
	
	private Handler threadHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.wilsonflying.testHandlerThread.R.layout.layout_communicate);
		
		button_send = (Button) findViewById(com.wilsonflying.testHandlerThread.R.id.button_send);
		button_stop = (Button) findViewById(com.wilsonflying.testHandlerThread.R.id.button_stop);
		button_send.setOnClickListener(this);
		button_stop.setOnClickListener(this);
		
		HandlerThread thread = new HandlerThread("myHandlerThread");
		thread.start();
		
		threadHandler = new Handler(thread.getLooper()){
			
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what == TO_SLAVE){
					System.out.println("in threadHandler, msg.arg:"+msg.arg1);
					Toast.makeText(getApplicationContext(), "in threadHandler, msg.arg:"+msg.arg1, Toast.LENGTH_SHORT).show();
					
					Message message = Message.obtain();
					message.what = TO_MASTER;
					message.arg1 = 88;
					handler.sendMessageDelayed(message, 3000);
				}
			}
		};

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case com.wilsonflying.testHandlerThread.R.id.button_send:
			Message msg = Message.obtain();
			msg.what = TO_SLAVE;
			threadHandler.sendMessage(msg);
			break;
		case com.wilsonflying.testHandlerThread.R.id.button_stop:
			//handler.removeMessages(1);
			handler.removeMessages(TO_MASTER);
			threadHandler.removeMessages(TO_SLAVE);
			break;
		default:
			break;
		}
	}

}
