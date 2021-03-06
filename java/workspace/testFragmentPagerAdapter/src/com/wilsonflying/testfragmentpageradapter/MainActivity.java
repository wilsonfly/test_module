package com.wilsonflying.testfragmentpageradapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private MyFragmentPagerAdapter mAdapter;
	private ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		setContentView(R.layout.layout_activity_main);
		
//		mAdapter = new MyFragmentPagerAdapter(getFragmentManager());
		mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(mAdapter);
	}

	public class MyFragment extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		@Nullable
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			// return super.onCreateView(inflater, container,
			// savedInstanceState);
			View view = inflater.inflate(R.layout.layout_section, null);
			TextView tv = (TextView) view.findViewById(R.id.tv);
			tv.setText("show something here:"+Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));

			return view;
		}

	}

	public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}


		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub

			MyFragment fragment = new MyFragment();
			Bundle args = new Bundle();
			args.putInt(MyFragment.ARG_SECTION_NUMBER, arg0);
			fragment.setArguments(args);

			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			// return super.getPageTitle(position);
			switch (position) {
			case 0:
				return "section 1";
			case 1:
				return "section 2";
			case 2:
				return "section 3";
			default:
				return null;
			}
		}

	}

}
