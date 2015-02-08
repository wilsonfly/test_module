package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	private int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09 }; // 声明并初始化一个保存要显示图像ID的数组
	private int index = 0; // 当前显示图像的索引
	private ImageSwitcher imageSwitcher; // 声明一个图像切换器对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
		imageSwitcher.setImageResource(imageId[index]);// 显示默认的图片
		Button up = (Button) findViewById(R.id.button1); // 获取“上一张”按钮
		Button down = (Button) findViewById(R.id.button2); // 获取“下一张”按钮
		up.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

					if (index > 0) {
						index--;
					} else {
						index = imageId.length - 1;
					}
				imageSwitcher.setImageResource(imageId[index]); // 显示当前图片
			}
		});
		down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (index < imageId.length - 1) {
					index++;
				} else {
					index = 0;
				}
				imageSwitcher.setImageResource(imageId[index]); // 显示当前图片
			}
		});	
//		down.setId(002); // 为“下一张”按钮设置ID属性
//		up.setId(001); // 为“上一张”按钮设置ID属性
//		// 实例化一个OnClickListener类的对象，并重写onClick()方法
//		OnClickListener ctrlOnClickListener = new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (v.getId() == 001) { // 如果是上一张按钮
//					if (index > 0) {
//						index--;
//					} else {
//						index = imageId.length - 1;
//					}
//				} else if (v.getId() == 002) { // 如果是下一张按钮
//					if (index < imageId.length - 1) {
//						index++;
//					} else {
//						index = 0;
//					}
//				}
//				imageSwitcher.setImageResource(imageId[index]); // 显示当前图片
//			}
//		};
//		up.setOnClickListener(ctrlOnClickListener); // 为上一张按钮添加单击事件监听器
//		down.setOnClickListener(ctrlOnClickListener); // 为下一张按钮添加单击事件监听器
	}
}