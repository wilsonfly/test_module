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
	final int NOTIFYID_1 = 123; // ��һ��֪ͨ��ID
	private String user="����";	//�û���
	private NotificationManager notificationManager;	//����֪ͨ����������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ֪ͨ�����������ڷ���֪ͨ
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Button button1 = (Button) findViewById(R.id.button1); // ��ȡ����¼����ť
		// Ϊ����¼����ť���ӵ����¼�������
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText etUser=(EditText)findViewById(R.id.user);
				if(!"".equals(etUser.getText())){
					user=etUser.getText().toString();
				}
				sendNotification(); // ����֪ͨ

			}
		});

		Button button2 = (Button) findViewById(R.id.button2); // ��ȡ���˳�����ť
		// Ϊ���˳�����ť���ӵ����¼�������
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				notificationManager.cancel(NOTIFYID_1); // ���֪ͨ
				((TableRow)findViewById(R.id.tableRow1)).setVisibility(View.VISIBLE);	//�ò����еĵ�һ����ʾ
				((TableRow)findViewById(R.id.tableRow2)).setVisibility(View.VISIBLE);	//�ò����еĵڶ�����ʾ
				((Button)findViewById(R.id.button1)).setText("��¼");	//�ı䡰���ĵ�¼״̬����ť����ʾ������
			}
		});
	}

	// ����֪ͨ
	private void sendNotification() {
		Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setIcon(R.drawable.advise); // ���öԻ����ͼ��
		builder.setTitle("�ҵĵ�¼״̬��"); // ���öԻ���ı���

		final int[] imageId = new int[] { R.drawable.img1, R.drawable.img2,
				R.drawable.img3, R.drawable.img4 }; // ���岢��ʼ������ͼƬid������
		final String[] title = new String[] { "����", "����", "æµ��", "����" }; // ���岢��ʼ�������б������ֵ�����
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // ����һ��list����
		// ͨ��forѭ����ͼƬid���б������ַŵ�Map�У������ӵ�list������
		for (int i = 0; i < imageId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>(); // ʵ����Map����
			map.put("image", imageId[i]);
			map.put("title", title[i]);
			listItems.add(map); // ��map�������ӵ�List������
		}
		final SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
				listItems, R.layout.items, new String[] { "title", "image" },
				new int[] { R.id.title, R.id.image }); // ����SimpleAdapter

		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Notification notify = new Notification(); // ����һ��Notification����
				notify.icon = imageId[which];
				notify.tickerText = title[which];
				notify.when = System.currentTimeMillis(); // ���÷���ʱ��
				notify.defaults = Notification.DEFAULT_SOUND; // ����Ĭ������
				notify.setLatestEventInfo(MainActivity.this, user,
						title[which], null);// �����¼���Ϣ
				notificationManager.notify(NOTIFYID_1, notify); // ͨ��֪ͨ����������֪ͨ
				((TableRow)findViewById(R.id.tableRow1)).setVisibility(View.INVISIBLE);	//�ò����еĵ�һ�в���ʾ
				((TableRow)findViewById(R.id.tableRow2)).setVisibility(View.INVISIBLE);	//�ò����еĵڶ��в���ʾ
				((Button)findViewById(R.id.button1)).setText("���ĵ�¼״̬");	//�ı䡰��¼����ť����ʾ������
			}
		});

		builder.create().show(); // �����Ի�����ʾ

	}

}