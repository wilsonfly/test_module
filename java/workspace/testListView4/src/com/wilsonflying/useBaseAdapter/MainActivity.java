package com.wilsonflying.useBaseAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends  Activity {

	
	private int[] imgIds = new int[]{R.drawable.bluesee, R.drawable.flower, R.drawable.meng};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		ListView lv = (ListView) findViewById(R.id.listview);
		lv.setAdapter(adapter);
		
	}
	
	BaseAdapter adapter = new BaseAdapter() {
		
		Custom[] data = new Custom[]{
				new Custom("andy", "desc of andy", imgIds[0]),	
				new Custom("wilson", "desc of wilson",  imgIds[1]),	
				new Custom("wilsonflying", "desc of wilsonflying", imgIds[2]),	
		};

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			LinearLayout ll = null;
			
			if(convertView != null){
				ll = (LinearLayout) convertView;
			}else
			{
				ll = (LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_layout, null);
			}

			Custom cus = getItem(position);
			ImageView img = (ImageView) ll.findViewById(R.id.imageview);//!!!注意这个地方应该是用ll.findviewbyid
			TextView name = (TextView) ll.findViewById(R.id.text1);
			TextView des = (TextView) ll.findViewById(R.id.text2);
			
			img.setImageResource(cus.imgid);
			name.setText(cus.getName());
			des.setText(cus.getDescription());
			
			return ll;
		}
		
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public Custom getItem(int position) {
			// TODO Auto-generated method stub
			return data[position];
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.length;
		}
	};
}
