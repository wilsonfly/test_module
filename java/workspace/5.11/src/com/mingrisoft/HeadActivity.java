package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class HeadActivity extends Activity {

	public String[] city = new String[] { "北京", "上海","广州","长春","沈阳","哈尔滨","天津","西安","杭州","深圳","南京","洛阳" }; 							// 定义并初始化保存头像id的数组
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.head);		//设置该Activity使用的布局
		GridView gridview = (GridView) findViewById(R.id.gridView1); 			// 获取GridView组件
		BaseAdapter adapter=new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView tv;							//声明ImageView的对象
				if(convertView==null){
					tv=new TextView(HeadActivity.this);		//实例化ImageView的对象
//					/*************设置图像的宽度和高度******************/
//					imageview.setAdjustViewBounds(true);
//					imageview.setMaxWidth(158);
//					imageview.setMaxHeight(150);
//					/**************************************************/
					tv.setPadding(5, 5, 5, 5);				//设置ImageView的内边距
				}else{
					tv=(TextView)convertView;
				}
				tv.setText(city[position]);		//为ImageView设置要显示的图片
				return tv;	//返回ImageView
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
				return city.length;
			}
		};
		
		gridview.setAdapter(adapter); 									// 将适配器与GridView关联
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				Intent intent=getIntent();	//获取Intent对象
				Bundle bundle=new Bundle();	//实例化要传递的数据包
				bundle.putString("city",city[position] );// 显示选中的图片
				intent.putExtras(bundle);	//将数据包保存到intent中
				setResult(0x11,intent);	//设置返回的结果码，并返回调用该Activity的Activity
				finish();		//关闭当前Activity
			}
		});
		
	}

}
