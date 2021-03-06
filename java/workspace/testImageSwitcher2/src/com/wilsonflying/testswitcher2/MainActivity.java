package com.wilsonflying.testswitcher2;

import com.wilsonflying.testimageswitcher2.R;

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
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	private int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09 };
	private ImageSwitcher imageswitcher;

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);

		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		imageswitcher = (ImageSwitcher) findViewById(R.id.imageswitcher);
		imageswitcher.setAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, android.R.anim.fade_in));
		imageswitcher.setAnimation(AnimationUtils.loadAnimation(
				MainActivity.this, android.R.anim.fade_out));
		imageswitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				ImageView iv = new ImageView(MainActivity.this);
				iv.setScaleType(ScaleType.FIT_CENTER);
				// iv.setLayoutParams(new LayoutParams(ImageSwitcher.LayoutParams.WRAP_CONTENT,ImageSwitcher.LayoutParams.WRAP_CONTENT));
				// iv.setLayoutParams( new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				// iv.setLayoutParams( new ImageSwitcher.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				iv.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.WRAP_CONTENT,ImageSwitcher.LayoutParams.WRAP_CONTENT));

				return iv;
			}
		});

		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ImageView imgview;
				if (convertView == null) {
					imgview = new ImageView(MainActivity.this);
					imgview.setScaleType(ScaleType.FIT_XY);
					// imgview.setLayoutParams(new LayoutParams(200, 100));
					imgview.setLayoutParams(new Gallery.LayoutParams(200, 150));
					TypedArray typedarray = obtainStyledAttributes(R.styleable.Gallery);
					imgview.setBackgroundResource(typedarray.getResourceId(
							R.styleable.Gallery_android_galleryItemBackground,
							0));
					imgview.setPadding(5, 0, 5, 0);
				} else {
					imgview = (ImageView) convertView;
				}
				imgview.setImageResource(imageId[position]);

				return imgview;
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
				return imageId.length;
			}
		};

		gallery.setAdapter(adapter);
		gallery.setSelection(imageId.length / 2);
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imageswitcher.setImageResource(imageId[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> param) {

			}
		});
	}
}
