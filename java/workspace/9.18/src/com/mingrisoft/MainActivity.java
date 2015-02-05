package com.mingrisoft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private List<String> imagePath = new ArrayList<String>();	//图片文件的路径

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String sdpath = Environment.getExternalStorageDirectory() + "/";
		getFiles(sdpath);		//调用getFiles()方法获取SD卡上的全部图片
		if(imagePath.size()<1){
			return;
		}
		GridView gridview = (GridView) findViewById(R.id.gridView1); // 获取GridView组件
		BaseAdapter adapter = new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // 声明ImageView的对象
				if (convertView == null) {
					imageview = new ImageView(MainActivity.this); // 实例化ImageView的对象
					/************* 设置图像的宽度和高度 ******************/
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(150);
					imageview.setMaxHeight(113);
					/**************************************************/
					imageview.setPadding(5, 5, 5, 5); // 设置ImageView的内边距
				} else {
					imageview = (ImageView) convertView;
				}

				// 为ImageView设置要显示的图片
				Bitmap bm=BitmapFactory.decodeFile(imagePath.get(position));
				imageview.setImageBitmap(bm);
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
				return imagePath.size();
			}
		};
		gridview.setAdapter(adapter); // 将适配器与GridView关联

	}
	//遍历指定的路径
	private void getFiles(String url) {
		File files = new File(url);	//创建文件对象
		File[] file = files.listFiles();
		try {
			for (File f : file) {	//通过for循环遍历获取到的文件数组
				if (f.isDirectory()) {	//如果是目录，也就是文件夹
					getFiles(f.getAbsolutePath()); // 递归调用
				} else {
					if (isImageFile(f.getPath())) {	//如果是图片文件
						imagePath.add(f.getPath());	//将文件的路径添加到list集合中
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String[] imageFormatSet = new String[] { "jpg", "png", "gif" };	//合法的文件格式
	//判断是否为图片文件
	private static boolean isImageFile(String path) {
		for (String format : imageFormatSet) {	//遍历数组
			if (path.contains(format)) {	//判断是否为有合法的图片文件
				return true;
			}
		}
		return false;
	}

}