package com.mingrisoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
				R.drawable.img03, R.drawable.img04, R.drawable.img05 }; // ���岢��ʼ������ͼƬid������
		final String[] title = new String[] { "�������", "��������", "��ȫ����", "�ʼ�����",
				"��������" }; // ���岢��ʼ�������б������ֵ�����
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // ����һ��list����
		// ͨ��forѭ����ͼƬid���б������ַŵ�Map�У������ӵ�list������
		for (int i = 0; i < imageId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>(); // ʵ����Map����
			map.put("image", imageId[i]);
			map.put("title", title[i]);
			listItems.add(map); // ��map�������ӵ�List������
		}

		final SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.items, new String[] { "title", "image" }, new int[] {
						R.id.title, R.id.image }); // ����SimpleAdapter
		// ���б��ĶԻ���
		Button button1 = (Button) findViewById(R.id.button1); // ��ȡ�����ļ������ӵİ�ť
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise); // ���öԻ����ͼ��
				builder.setTitle("���ã�"); // ���öԻ���ı���
				// �����б���
				builder.setAdapter(adapter, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this,
								"��ѡ����[ " + title[which]+" ]", Toast.LENGTH_SHORT)
								.show();

					}
				});
				builder.create().show(); // �����Ի�����ʾ
			}
		});

	}
}