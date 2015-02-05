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
			R.drawable.img12}; // 定义并初始化保存图片id的数组

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); // 设置该Activity使用的布局
		GridView gridview = (GridView) findViewById(R.id.gridView1); // 获取GridView组件
		BaseAdapter adapter = new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // 声明ImageView的对象
				if (convertView == null) {
					imageview = new ImageView(MainActivity.this); // 实例化ImageView的对象
					/************* 设置图像的宽度和高度 ******************/
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(180);
					imageview.setMaxHeight(135);
					/**************************************************/
					imageview.setPadding(5, 5, 5, 5); // 设置ImageView的内边距
				} else {
					imageview = (ImageView) convertView;
				}
				imageview.setImageResource(imageId[position]); // 为ImageView设置要显示的图片
				return imageview; // 返回ImageView
			}

			/*
			 * 功能：获得当前选项的ID
			 */
			@Override
			public long getItemId(int position) {
				return position;
			}

			/*
			 * 功能：获得当前选项
			 */
			@Override
			public Object getItem(int position) {
				return position;
			}

			/*
			 * 获得数量
			 */
			@Override
			public int getCount() {
				return imageId.length;
			}
		};

		gridview.setAdapter(adapter); // 将适配器与GridView关联
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this, BigActivity.class);
				Bundle bundle = new Bundle(); // 创建并实例化一个Bundle对象
				bundle.putInt("imgId", imageId[position]); // 保存图片ID
				intent.putExtras(bundle); // 将Bundle对象添加到Intent对象中
				startActivity(intent); // 启动新的Activity

			}
		});

	}
}
