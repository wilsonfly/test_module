package com.wilsonflying.testdialog;

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
				R.drawable.img03, R.drawable.img04, R.drawable.img05 }; // 定义并初始化保存图片id的数组
		final String[] title = new String[] { "程序管理", "保密设置", "安全设置", "邮件设置",
				"铃声设置" }; // 定义并初始化保存列表项文字的数组
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // 创建一个list集合
		// 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
		for (int i = 0; i < imageId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
			map.put("image", imageId[i]);
			map.put("title", title[i]);
			listItems.add(map); // 将map对象添加到List集合中
		}

		final SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.items, new String[] { "title", "image" }, new int[] {
						R.id.title, R.id.image }); // 创建SimpleAdapter
		// 带列表的对话框
		Button button1 = (Button) findViewById(R.id.button1); // 获取布局文件中添加的按钮
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise); // 设置对话框的图标
				builder.setTitle("设置："); // 设置对话框的标题
				// 添加列表项
				builder.setAdapter(adapter, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this,
								"您选择了[ " + title[which]+" ]", Toast.LENGTH_SHORT)
								.show();

					}
				});
				builder.create().show(); // 创建对话框并显示
			}
		});

	}
}