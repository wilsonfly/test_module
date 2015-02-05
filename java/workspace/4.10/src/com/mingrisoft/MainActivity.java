package com.mingrisoft;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	private int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09, R.drawable.img10, R.drawable.img11,
			R.drawable.img12, }; // 定义并初始化保存图片id的数组
	private ImageSwitcher imageSwitcher; // 声明一个图像切换器对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Gallery gallery = (Gallery) findViewById(R.id.gallery1); // 获取Gallery组件
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
		/********************** 使用BaseAdapter指定要显示的内容 *****************************/
		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // 声明ImageView的对象
				if (convertView == null) {
					imageview = new ImageView(MainActivity.this); // 实例化ImageView的对象
					imageview.setScaleType(ImageView.ScaleType.FIT_XY); // 设置缩放方式
					imageview
							.setLayoutParams(new Gallery.LayoutParams(180, 135));
					TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
					imageview.setBackgroundResource(typedArray.getResourceId(
							R.styleable.Gallery_android_galleryItemBackground,
							0));
					imageview.setPadding(5, 0, 5, 0); // 设置ImageView的内边距
				} else {
					imageview = (ImageView) convertView;
				}
				imageview.setImageResource(imageId[position]); // 为ImageView设置要显示的图片
				return imageview; // 返回ImageView
			}

			/*
			 * 功能：获得当前选项的ID (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getItemId(int)
			 */
			@Override
			public long getItemId(int position) {
				return position;
			}

			/*
			 * 功能：获得当前选项 (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getItem(int)
			 */
			@Override
			public Object getItem(int position) {
				return position;
			}

			/*
			 * 获得数量 (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getCount()
			 */
			@Override
			public int getCount() {
				return imageId.length;
			}
		};
		gallery.setAdapter(adapter); // 将适配器与Gallery关联
		/*********************************************************************************/
		gallery.setSelection(imageId.length / 2); // 让中间的图片选中
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imageSwitcher.setImageResource(imageId[position]);// 显示选中的图片
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
	}
}