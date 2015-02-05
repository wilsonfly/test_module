package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Demonstration of using fragments to implement different activity layouts.
 * This sample provides a different layout (and activity flow) when run in
 * landscape.
 */
public class MainActivity extends Activity {

	public int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09, R.drawable.img10, R.drawable.img11,
			R.drawable.img12}; // ���岢��ʼ������ͼƬid������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); // ���ø�Activityʹ�õĲ���
		GridView gridview = (GridView) findViewById(R.id.gridView1); // ��ȡGridView���
		BaseAdapter adapter = new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // ����ImageView�Ķ���
				if (convertView == null) {
					imageview = new ImageView(MainActivity.this); // ʵ����ImageView�Ķ���
					/************* ����ͼ��Ŀ�Ⱥ͸߶� ******************/
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(180);
					imageview.setMaxHeight(135);
					/**************************************************/
					imageview.setPadding(5, 5, 5, 5); // ����ImageView���ڱ߾�
				} else {
					imageview = (ImageView) convertView;
				}
				imageview.setImageResource(imageId[position]); // ΪImageView����Ҫ��ʾ��ͼƬ
				return imageview; // ����ImageView
			}

			/*
			 * ���ܣ���õ�ǰѡ���ID
			 */
			@Override
			public long getItemId(int position) {
				return position;
			}

			/*
			 * ���ܣ���õ�ǰѡ��
			 */
			@Override
			public Object getItem(int position) {
				return position;
			}

			/*
			 * �������
			 */
			@Override
			public int getCount() {
				return imageId.length;
			}
		};

		gridview.setAdapter(adapter); // ����������GridView����
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this, BigActivity.class);
				Bundle bundle = new Bundle(); // ������ʵ����һ��Bundle����
				bundle.putInt("imgId", imageId[position]); // ����ͼƬID
				intent.putExtras(bundle); // ��Bundle������ӵ�Intent������
				startActivity(intent); // �����µ�Activity

			}
		});

	}
}
