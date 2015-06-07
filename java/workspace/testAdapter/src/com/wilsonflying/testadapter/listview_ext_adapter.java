package com.wilsonflying.testadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListView_ext_adapter extends BaseAdapter {

	private cellData_listview_ext[] data = new cellData_listview_ext[]{
			new cellData_listview_ext("李雷", "男", R.drawable.img1),
			new cellData_listview_ext("韩梅梅", "女", R.drawable.img2),
			};
	private Context context;
	
	public ListView_ext_adapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public Context getTheContext(){
		return this.context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout ll = null;
		if(convertView != null){
			ll = (LinearLayout) convertView;
		}else
		{
//			LayoutInflater inflater = LayoutInflater.from(this);//需要一个context，this则是一个listview_ext_adapter
			LayoutInflater inflater = LayoutInflater.from(getTheContext());
			ll = (LinearLayout) inflater.inflate(R.layout.layout_celldata_listview_ext, null);
		}
		
		cellData_listview_ext celldata = (cellData_listview_ext) getItem(position);
		
		ImageView image = (ImageView) ll.findViewById(R.id.imageview);
		TextView tv_name = (TextView) ll.findViewById(R.id.tv_name);
		TextView tv_des = (TextView) ll.findViewById(R.id.tv_description);
		
		image.setImageResource(celldata.getIconId());
		tv_name.setText(celldata.getName());
		tv_des.setText(celldata.getDescription());
		
		return ll;
	}

}
