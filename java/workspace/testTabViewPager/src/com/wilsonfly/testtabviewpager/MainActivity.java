package com.wilsonfly.testtabviewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {

	private ViewPager mViewPager;
	private PagerAdapter mAdapter;
	private List<View> mViews = new ArrayList<View>();

	private LinearLayout mTabWeixin;
	private LinearLayout mTabAddress;
	private LinearLayout mTabFind;
	private LinearLayout mTabMe;

	private ImageButton mImgBtnWeixin;
	private ImageButton mImgBtnAddress;
	private ImageButton mImgBtnFind;
	private ImageButton mImgBtnMe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();

		initEvent();
	}

	private void initEvent() {
		// TODO Auto-generated method stub

		mTabWeixin.setOnClickListener(this);
		mTabAddress.setOnClickListener(this);
		mTabFind.setOnClickListener(this);
		mTabMe.setOnClickListener(this);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
				resetImg();
				
				switch (arg0) {
				case 0:
					mImgBtnWeixin.setImageResource(R.drawable.selected);
					break;
				case 1:
					mImgBtnAddress.setImageResource(R.drawable.selected);
					break;
				case 2:
					mImgBtnFind.setImageResource(R.drawable.selected);
					break;
				case 3:
					mImgBtnMe.setImageResource(R.drawable.selected);
					break;

				default:
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		mTabWeixin = (LinearLayout) findViewById(R.id.tabWeixin);
		mTabAddress = (LinearLayout) findViewById(R.id.tabAddress);
		mTabFind = (LinearLayout) findViewById(R.id.tabFind);
		mTabMe = (LinearLayout) findViewById(R.id.tabMe);

		mImgBtnWeixin = (ImageButton) findViewById(R.id.imgBtnWeixin);
		mImgBtnAddress = (ImageButton) findViewById(R.id.imgBtnAddress);
		mImgBtnFind = (ImageButton) findViewById(R.id.imgBtnFind);
		mImgBtnMe = (ImageButton) findViewById(R.id.imgBtnMe);

		LayoutInflater inflater = LayoutInflater.from(this);
		View tab01 = inflater.inflate(R.layout.tab01, null);
		View tab02 = inflater.inflate(R.layout.tab02, null);
		View tab03 = inflater.inflate(R.layout.tab03, null);
		View tab04 = inflater.inflate(R.layout.tab04, null);
		mViews.add(tab01);
		mViews.add(tab02);
		mViews.add(tab03);
		mViews.add(tab04);

		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				// super.destroyItem(container, position, object);
				container.removeView(mViews.get(position));

			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				// return super.instantiateItem(container, position);
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mViews.size();
			}
		};

		mViewPager.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		resetImg();
		
		switch (v.getId()) {
		case R.id.tabWeixin:
			mViewPager.setCurrentItem(0);
			mImgBtnWeixin.setImageResource(R.drawable.selected);
			break;
		case R.id.tabAddress:
			mViewPager.setCurrentItem(1);
			mImgBtnAddress.setImageResource(R.drawable.selected);

			break;
		case R.id.tabFind:
			mViewPager.setCurrentItem(2);
			mImgBtnFind.setImageResource(R.drawable.selected);


			break;
		case R.id.tabMe:
			mViewPager.setCurrentItem(3);
			mImgBtnMe.setImageResource(R.drawable.selected);


			break;

		default:
			break;
		}
	}

	//图片切换回普通状态
	private void resetImg() {
		// TODO Auto-generated method stub
		mImgBtnWeixin.setImageResource(R.drawable.weixin);
		mImgBtnAddress.setImageResource(R.drawable.address);
		mImgBtnFind.setImageResource(R.drawable.find);
		mImgBtnMe.setImageResource(R.drawable.me);
	}

}
