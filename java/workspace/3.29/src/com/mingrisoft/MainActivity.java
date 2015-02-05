package com.mingrisoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView listview = (ListView) findViewById(R.id.listView1); // ��ȡ�б���ͼ
		int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
				R.drawable.img03, R.drawable.img04, R.drawable.img05,
				R.drawable.img06, R.drawable.img07, R.drawable.img08 }; // ���岢��ʼ������ͼƬid������
		String[] title = new String[] { "��������", "��ȫ", "ϵͳ����", "����", "�ҵ��ĵ�",
				"GPS����", "�ҵ�����", "E-mail" }; // ���岢��ʼ�������б������ֵ�����
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // ����һ��list����
		// ͨ��forѭ����ͼƬid���б������ַŵ�Map�У�����ӵ�list������
		for (int i = 0; i < imageId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>(); // ʵ����Map����
			map.put("image", imageId[i]);
			map.put("title", title[i]);
			listItems.add(map); // ��map������ӵ�List������
		}

		SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.items, new String[] { "title", "image" }, new int[] {
						R.id.title, R.id.image }); // ����SimpleAdapter
		listview.setAdapter(adapter); // ����������ListView����

	}
}