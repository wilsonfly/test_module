package com.wilsonflying.testfragment2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailFragment extends Fragment {

	public static DetailFragment newInstance(int index){
		DetailFragment fg = new DetailFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		fg.setArguments(bundle);
		
		return fg;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		
		ScrollView view = new ScrollView(getActivity());
		TextView tv = new TextView(getActivity());
		int index = getArguments().getInt("index", 0);//读取DetailAty.onCreate中commit transaction之前setArgument传过来的index参数
		tv.setText(Data.DETAILS[index]);
		tv.setPadding(10, 10, 10, 10);
		
		view.addView(tv);
		
		return view;
	}
}



