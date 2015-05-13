package com.wilsonflying.testgallery;


import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;

public class MainActivity extends Activity {
	int[] imgId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09, R.drawable.img10, R.drawable.img11,
			R.drawable.img12 };

	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		
		Gallery g = (Gallery) findViewById(R.id.gallery);
		
		BaseAdapter adapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ImageView imageview;
				if(convertView == null){
					imageview = new ImageView(MainActivity.this);
					imageview.setScaleType(ScaleType.FIT_XY);
					imageview.setLayoutParams(new android.widget.Gallery.LayoutParams(200,150));
					TypedArray typedarray = obtainStyledAttributes(R.styleable.Gallery);
					imageview.setBackgroundResource(typedarray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));
					imageview.setPadding(5, 0, 5, 0);
				}
				else{
					imageview = (ImageView) convertView;
				}
				
				imageview.setImageResource(imgId[position]);
				
				return imageview;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return imgId.length;
			}
		}; 
		
		g.setAdapter(adapter);
		g.setSelection(imgId.length/2);
		g.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?>parent, View view, int position, long id){
				Toast.makeText(MainActivity.this, "点击了第"+String.valueOf(position)+"张图片", Toast.LENGTH_SHORT).show();
			}
			
		});
	}
}
