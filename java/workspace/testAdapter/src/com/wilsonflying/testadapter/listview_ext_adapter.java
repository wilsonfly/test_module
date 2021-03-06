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

	private cellData_listview_ext[] data = new cellData_listview_ext[] {
			new cellData_listview_ext("李雷", "男", R.drawable.img1),
			new cellData_listview_ext("韩梅梅", "女", R.drawable.img2), };
	private Context context;

	public ListView_ext_adapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public Context getTheContext() {
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
//		LayoutInflater inflater = LayoutInflater.from(this);//需要一个view的context，而this只是一个listview_ext_adapter
		LayoutInflater inflater = LayoutInflater.from(getTheContext());
		
		//======终极版，使用了convertView(避免每次new 一个View)及viewHolder(避免每次去findViewById)======//
		ViewHolder viewHolder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.layout_celldata_listview_ext, null);
			
			viewHolder = new ViewHolder();
			viewHolder.setImageView((ImageView) convertView.findViewById(R.id.imageview));
			viewHolder.setTv_name((TextView) convertView.findViewById(R.id.tv_name));
			viewHolder.setTv_des((TextView) convertView.findViewById(R.id.tv_description));
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		cellData_listview_ext celldata = (cellData_listview_ext) getItem(position);
		
		ImageView image = viewHolder.getImageView();
		TextView tv_name = viewHolder.getTv_name();
		TextView tv_des = viewHolder.getTv_des();
		
		image.setImageResource(celldata.getIconId());
		tv_name.setText(celldata.getName());
		tv_des.setText(celldata.getDescription());
		
		return convertView;
		//======终极版，使用了convertView(避免每次new 一个View)及viewHolder(避免每次去findViewById)======//

		//======继续进阶，使用了convertView(避免每次new 一个View)======//
//		if(convertView == null){
//			convertView = (LinearLayout) inflater.inflate(R.layout.layout_celldata_listview_ext, null);
//		}
//		
//		cellData_listview_ext celldata = (cellData_listview_ext) getItem(position);
//		
//		ImageView image = (ImageView) convertView.findViewById(R.id.imageview);
//		TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
//		TextView tv_des = (TextView) convertView.findViewById(R.id.tv_description);
//		
//		image.setImageResource(celldata.getIconId());
//		tv_name.setText(celldata.getName());
//		tv_des.setText(celldata.getDescription());
//		
//		return convertView;
		//======继续进阶，使用了convertView(避免每次new 一个View)======//
		
		//======进阶，其实没有必要每次新建一个变量ll，完全可以使用converView，无论其是否为空======//
//		LinearLayout ll = null;
//		if(convertView != null){
//			ll = (LinearLayout) convertView;
//		}else
//		{
//			ll = (LinearLayout) inflater.inflate(R.layout.layout_celldata_listview_ext, null);
//		}
//		
//		cellData_listview_ext celldata = (cellData_listview_ext) getItem(position);
//		
//		ImageView image = (ImageView) ll.findViewById(R.id.imageview);
//		TextView tv_name = (TextView) ll.findViewById(R.id.tv_name);
//		TextView tv_des = (TextView) ll.findViewById(R.id.tv_description);
//		
//		image.setImageResource(celldata.getIconId());
//		tv_name.setText(celldata.getName());
//		tv_des.setText(celldata.getDescription());
//		
//		return ll;
		//======进阶，其实没有必要每次新建一个变量，完全可以使用converView，无论其是否为空======//

		//======性能最糟糕的情况======//
//		View view = new View(getTheContext());
//		view = (LinearLayout) inflater.inflate(R.layout.layout_celldata_listview_ext, null);
//		
//		cellData_listview_ext celldata = (cellData_listview_ext) getItem(position);
//		
//		ImageView image = (ImageView) view.findViewById(R.id.imageview);
//		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
//		TextView tv_des = (TextView) view.findViewById(R.id.tv_description);
//		
//		image.setImageResource(celldata.getIconId());
//		tv_name.setText(celldata.getName());
//		tv_des.setText(celldata.getDescription());
//		
//		return view;
		//======性能最糟糕的情况======//

	}

	class ViewHolder {
		public ImageView getImageView() {
			return imageView;
		}

		public void setImageView(ImageView imageView) {
			this.imageView = imageView;
		}

		public TextView getTv_name() {
			return tv_name;
		}

		public void setTv_name(TextView tv_name) {
			this.tv_name = tv_name;
		}

		public TextView getTv_des() {
			return tv_des;
		}

		public void setTv_des(TextView tv_des) {
			this.tv_des = tv_des;
		}

		private ImageView imageView;
		private TextView tv_name;
		private TextView tv_des;
	}

}
