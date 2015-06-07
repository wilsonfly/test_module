package com.wilsonflying.testviewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Guide extends Activity implements OnPageChangeListener{
  
	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	private ImageView dots[];
	private int ids[]={R.id.iv1,R.id.iv2,R.id.iv3};
	private Button button_start;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_guide);
		initViews();
		initDots();
		
	}
	
	public void initViews(){
		LayoutInflater inflater = LayoutInflater.from(this);
		
		views = new ArrayList<View>();
		views.add(inflater.inflate(R.layout.layout_one, null));
		views.add(inflater.inflate(R.layout.layout_two, null));
		views.add(inflater.inflate(R.layout.layout_three, null));
		
		vpAdapter = new ViewPagerAdapter(views, this);
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		
		vp.setOnPageChangeListener(this);
		
//		button_start = (Button) findViewById(R.id.button_start);
		button_start = (Button) views.get(2).findViewById(R.id.button_start);//!!!需要用layout_three的view来findviewbyid!!!
		button_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Guide.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	public void initDots(){
		dots = new ImageView[views.size()];
		for (int i = 0; i < views.size(); i++) {
			dots[i] = (ImageView) findViewById(ids[i]);
			
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		for (int i = 0; i < views.size(); i++) {
			if(i== arg0)
				dots[i].setImageResource(R.drawable.login_point_selected);
			else
				dots[i].setImageResource(R.drawable.login_point);
				
		}
		
	}

}
