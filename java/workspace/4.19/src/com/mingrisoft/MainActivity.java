package com.mingrisoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	private int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09, R.drawable.img10, R.drawable.img11,
			R.drawable.img12, }; // ���岢��ʼ������ͼƬid������
	final String[] filename = new String[] { "img01.jpg", "img02.jpg", "img03.jpg", "img04.jpg",
	"img05.jpg","img06.jpg","img07.jpg","img08.jpg","img09.jpg","img10.jpg","img11.jpg","img12.jpg" }; // ���岢��ʼ�������б������ֵ�����

	private ImageSwitcher imageSwitcher; // ����һ��ͼ���л�������
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/**********************ʹ��SimpleAdapterָ��Ҫ��ʾ������*****************************/
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // ����һ��list����
		// ͨ��forѭ����ͼƬid���б������ַŵ�Map�У������ӵ�list������
		for (int i = 0; i < imageId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>(); // ʵ����Map����
			map.put("image", imageId[i]);
			map.put("title", filename[i]);
			listItems.add(map); // ��map�������ӵ�List������
		}

		final SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.items, new String[] { "title", "image" }, new int[] {
						R.id.title, R.id.image }); // ����SimpleAdapter
		/*********************************************************************************/		
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1); // ��ȡͼ���л���
		// ���ö���Ч��
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in)); // ���õ��붯��
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out)); // ���õ�������
		imageSwitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				ImageView imageView = new ImageView(MainActivity.this); // ʵ����һ��ImageView��Ķ���
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // ���ñ����ݺ�Ⱦ�������ͼ��
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				return imageView; // ����imageView����
			}

		});
		imageSwitcher.setImageResource(imageId[6]);// ����Ĭ����ʾ��ͼ��
		GridView gridview = (GridView) findViewById(R.id.gridView1); // ��ȡGridView���
		gridview.setAdapter(adapter); // ����������GridView����
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				imageSwitcher.setImageResource(imageId[position]);// ��ʾѡ�е�ͼƬ
				
			}
			
		});
	}
}