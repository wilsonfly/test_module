package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
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
		/**********************使用BaseAdapter指定要显示的内容*****************************/
		BaseAdapter adapter=new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview;	//声明ImageView的对象
				if(convertView==null){
					imageview=new ImageView(MainActivity.this);	//实例化ImageView的对象
					/*************设置图像的宽度和高度******************/
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(150);
					imageview.setMaxHeight(113);
					/**************************************************/
					imageview.setPadding(5, 5, 5, 5);		//设置ImageView的内边距
				}else{
					imageview=(ImageView)convertView;
				}
				imageview.setImageResource(imageId[position]);	//为ImageView设置要显示的图片
				return imageview;	//返回ImageView
			}
			
			/* 
			 * 功能：获得当前选项的ID
			 * (non-Javadoc)
			 * @see android.widget.Adapter#getItemId(int)
			 */
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			/* 
			 * 功能：获得当前选项
			 * (non-Javadoc)
			 * @see android.widget.Adapter#getItem(int)
			 */
			@Override
			public Object getItem(int position) {
				return position;
			}
			
			/*
			 * 获得数量
			 *  (non-Javadoc)
			 * @see android.widget.Adapter#getCount()
			 */
			@Override
			public int getCount() {
				return imageId.length;
			}
		};
		/*********************************************************************************/
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