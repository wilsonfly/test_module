package com.mingrisoft;

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
	final int NOTIFYID_1 = 123; // 第一个通知的ID
	private String user="匿名";	//用户名
	private NotificationManager notificationManager;	//定义通知管理器对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取通知管理器，用于发送通知
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Button button1 = (Button) findViewById(R.id.button1); // 获取“登录”按钮
		// 为“登录”按钮添加单击事件监听器
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText etUser=(EditText)findViewById(R.id.user);
				if(!"".equals(etUser.getText())){
					user=etUser.getText().toString();
				}
				sendNotification(); // 发送通知

			}
		});

		Button button2 = (Button) findViewById(R.id.button2); // 获取“退出”按钮
		// 为“退出”按钮添加单击事件监听器
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				notificationManager.cancel(NOTIFYID_1); // 清除通知
				((TableRow)findViewById(R.id.tableRow1)).setVisibility(View.VISIBLE);	//让布局中的第一行显示
				((TableRow)findViewById(R.id.tableRow2)).setVisibility(View.VISIBLE);	//让布局中的第二行显示
				((Button)findViewById(R.id.button1)).setText("登录");	//改变“更改登录状态”按钮上显示的文字
			}
		});
	}

	// 发送通知
	private void sendNotification() {
		Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setIcon(R.drawable.advise); // 设置对话框的图标
		builder.setTitle("我的登录状态："); // 设置对话框的标题

		final int[] imageId = new int[] { R.drawable.img1, R.drawable.img2,
				R.drawable.img3, R.drawable.img4 }; // 定义并初始化保存图片id的数组
		final String[] title = new String[] { "在线", "隐身", "忙碌中", "离线" }; // 定义并初始化保存列表项文字的数组
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // 创建一个list集合
		// 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
		for (int i = 0; i < imageId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
			map.put("image", imageId[i]);
			map.put("title", title[i]);
			listItems.add(map); // 将map对象添加到List集合中
		}
		final SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
				listItems, R.layout.items, new String[] { "title", "image" },
				new int[] { R.id.title, R.id.image }); // 创建SimpleAdapter

		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Notification notify = new Notification(); // 创建一个Notification对象
				notify.icon = imageId[which];
				notify.tickerText = title[which];
				notify.when = System.currentTimeMillis(); // 设置发送时间
				notify.defaults = Notification.DEFAULT_SOUND; // 设置默认声音
				notify.setLatestEventInfo(MainActivity.this, user,
						title[which], null);// 设置事件信息
				notificationManager.notify(NOTIFYID_1, notify); // 通过通知管理器发送通知
				((TableRow)findViewById(R.id.tableRow1)).setVisibility(View.INVISIBLE);	//让布局中的第一行不显示
				((TableRow)findViewById(R.id.tableRow2)).setVisibility(View.INVISIBLE);	//让布局中的第二行不显示
				((Button)findViewById(R.id.button1)).setText("更改登录状态");	//改变“登录”按钮上显示的文字
			}
		});

		builder.create().show(); // 创建对话框并显示

	}

}