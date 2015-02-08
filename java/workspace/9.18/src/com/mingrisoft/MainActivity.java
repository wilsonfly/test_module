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
	private List<String> imagePath = new ArrayList<String>();	//ͼƬ�ļ���·��

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String sdpath = Environment.getExternalStorageDirectory() + "/";
		getFiles(sdpath);		//����getFiles()������ȡSD���ϵ�ȫ��ͼƬ
		if(imagePath.size()<1){
			return;
		}
		GridView gridview = (GridView) findViewById(R.id.gridView1); // ��ȡGridView���
		BaseAdapter adapter = new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // ����ImageView�Ķ���
				if (convertView == null) {
					imageview = new ImageView(MainActivity.this); // ʵ����ImageView�Ķ���
					/************* ����ͼ��Ŀ�Ⱥ͸߶� ******************/
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(150);
					imageview.setMaxHeight(113);
					/**************************************************/
					imageview.setPadding(5, 5, 5, 5); // ����ImageView���ڱ߾�
				} else {
					imageview = (ImageView) convertView;
				}

				// ΪImageView����Ҫ��ʾ��ͼƬ
				Bitmap bm=BitmapFactory.decodeFile(imagePath.get(position));
				imageview.setImageBitmap(bm);
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
				return imagePath.size();
			}
		};
		gridview.setAdapter(adapter); // ����������GridView����

	}
	//����ָ����·��
	private void getFiles(String url) {
		File files = new File(url);	//�����ļ�����
		File[] file = files.listFiles();
		try {
			for (File f : file) {	//ͨ��forѭ��������ȡ�����ļ�����
				if (f.isDirectory()) {	//�����Ŀ¼��Ҳ�����ļ���
					getFiles(f.getAbsolutePath()); // �ݹ����
				} else {
					if (isImageFile(f.getPath())) {	//�����ͼƬ�ļ�
						imagePath.add(f.getPath());	//���ļ���·����ӵ�list������
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String[] imageFormatSet = new String[] { "jpg", "png", "gif" };	//�Ϸ����ļ���ʽ
	//�ж��Ƿ�ΪͼƬ�ļ�
	private static boolean isImageFile(String path) {
		for (String format : imageFormatSet) {	//��������
			if (path.contains(format)) {	//�ж��Ƿ�Ϊ�кϷ���ͼƬ�ļ�
				return true;
			}
		}
		return false;
	}

}