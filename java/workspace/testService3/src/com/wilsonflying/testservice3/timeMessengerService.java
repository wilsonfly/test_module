package com.wilsonflying.testservice3;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.text.format.Time;
import android.widget.Toast;

public class timeMessengerService extends Service {

	public final static int GET_TIME = 100;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Messenger msgr = new Messenger(new incomingHandler()); 
		return msgr.getBinder();
	}

	public class incomingHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case GET_TIME:
				Time time = new Time();
				time.setToNow();
				String currentTime = time.format("%Y-%m-%d %H:%M:%S");
				Toast.makeText(getApplicationContext(), "time:"+currentTime, Toast.LENGTH_SHORT).show();
				break;

			default:
				super.handleMessage(msg);
				break;
			}
		}
	}
}
