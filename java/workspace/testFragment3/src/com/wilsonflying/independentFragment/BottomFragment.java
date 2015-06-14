package com.wilsonflying.independentFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BottomFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.layout_bottomfragment, null);
		return view;
	}
	
	public void updateText(String str){
		TextView tv = (TextView) getView().findViewById(R.id.tv_bottomfragment);
		tv.setText(str);
	}
}
