package com.wilsonflying.testimageswticher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wilsonflying.testimageswitcher.R;


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
			R.drawable.img12, }; // 定义并初始化保存图片id的数组
	final String[] filename = new String[] { "img01.jpg", "img02.jpg", "img03.jpg", "img04.jpg",
	"img05.jpg","img06.jpg","img07.jpg","img08.jpg","img09.jpg","img10.jpg","img11.jpg","img12.jpg" }; // 定义并初始化保存列表项文字的数组

	private ImageSwitcher imageSwitcher; // 声明一个图像切换器对象
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/**********************使用SimpleAdapter指定要显示的内容*****************************/
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); // 创建一个list集合
		// 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
		for (int i = 0; i < imageId.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>(); // 实例化Map对象
			map.put("image", imageId[i]);
			map.put("title", filename[i]);
			listItems.add(map); // 将map对象添加到List集合中
		}

		final SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.items, new String[] { "title", "image" }, new int[] {
						R.id.title, R.id.image }); // 创建SimpleAdapter
		/*********************************************************************************/		
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1); // 获取图像切换器
		// 设置动画效果
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in)); // 设置淡入动画
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out)); // 设置淡出动画
		imageSwitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				ImageView imageView = new ImageView(MainActivity.this); // 实例化一个ImageView类的对象
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // 设置保持纵横比居中缩放图像
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				return imageView; // 返回imageView对象
			}

		});
		imageSwitcher.setImageResource(imageId[6]);// 设置默认显示的图像
		GridView gridview = (GridView) findViewById(R.id.gridView1); // 获取GridView组件
		gridview.setAdapter(adapter); // 将适配器与GridView关联
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				imageSwitcher.setImageResource(imageId[position]);// 显示选中的图片
				
			}
			
		});
	}
}