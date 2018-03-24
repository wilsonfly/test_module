package com.wilsonflying.testnotification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TableRow;

public class MainActivity extends Activity {
	final int NOTIFYID_1 = 123; // 第一个登录的ID
	private String user="匿名";	//用户名
	private NotificationManager notificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Button button1 = (Button) findViewById(R.id.button1); //获取登录按钮
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText etUser=(EditText)findViewById(R.id.user);
				if(!"".equals(etUser.getText())){
					user=etUser.getText().toString();
				}
				sendNotification();

			}
		});

		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				notificationManager.cancel(NOTIFYID_1);
				((TableRow)findViewById(R.id.tableRow1)).setVisibility(View.VISIBLE);
				((TableRow)findViewById(R.id.tableRow2)).setVisibility(View.VISIBLE);
				((Button)findViewById(R.id.button1)).setText("登录");
			}
		});
	}

	//发送通知
	private void sendNotification() {
		Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setIcon(R.drawable.advise);
		builder.setTitle("我的登录状态");

		final int[] imageId = new int[] { R.drawable.img1, R.drawable.img2,
				R.drawable.img3, R.drawable.img4 };
		final String[] title = new String[] { "在线", "隐身", "忙碌中", "离线" };
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imageId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", imageId[i]);
			map.put("title", title[i]);
			listItems.add(map);
		}
		final SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
				listItems, R.layout.items, new String[] { "title", "image" },
				new int[] { R.id.title, R.id.image });

		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Notification notify = new Notification();
				notify.icon = imageId[which];
				notify.tickerText = title[which];
				notify.when = System.currentTimeMillis();
				notify.defaults = Notification.DEFAULT_SOUND;
				notify.setLatestEventInfo(MainActivity.this, user,
						title[which], null);//设置事件信息
				notificationManager.notify(NOTIFYID_1, notify);
				((TableRow)findViewById(R.id.tableRow1)).setVisibility(View.INVISIBLE);	//布局中的第一行不显示
				((TableRow)findViewById(R.id.tableRow2)).setVisibility(View.INVISIBLE);	//不居中的第二行不显示
				((Button)findViewById(R.id.button1)).setText("更改登录状态");	//改变“登录”按钮上显示的文字
			}
		});

		builder.create().show();

	}

}