package com.wilsonflying.testnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private final int NOTIFICATION_NO1=100;
	private final int NOTIFICATION_NO2=200;
	
	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		
		final NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		Button button_nofify = (Button) findViewById(R.id.button_notification);
		Button button_clear = (Button) findViewById(R.id.button_clearNotification);
		
		button_nofify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Notification ntf = new Notification();
				ntf.icon = R.drawable.advise;
				ntf.defaults = Notification.DEFAULT_ALL;
				ntf.tickerText = "��һ��֪ͨ�����ݸ�Ҫ";
				ntf.when = System.currentTimeMillis();
				ntf.setLatestEventInfo(MainActivity.this, "titile1", "������ʲô", null);
				nm.notify(NOTIFICATION_NO1, ntf);
				
				//�ڶ���notification
				Notification ntf2 = new Notification(R.drawable.advise2, "�ڶ���֪ͨ�����ݸ�Ҫ", System.currentTimeMillis());
				ntf2.flags |= Notification.FLAG_AUTO_CANCEL;
				Intent intent = new Intent(MainActivity.this, ContentActivity.class);
				PendingIntent pendingintent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				ntf2.setLatestEventInfo(MainActivity.this, "title2", "������ʲô", pendingintent);
				nm.notify(NOTIFICATION_NO2, ntf2);
				
			}
		});
		
		button_clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//nm.cancel(NOTIFICATION_NO1);
				//nm.cancel(NOTIFICATION_NO2);
				nm.cancelAll();
			}
		});
	}
}