package com.wilsonflying.transArgument;

import com.wilsonflying.transArgument.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BottomFragmentDynamic extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
//		View view = getActivity().findViewById(R.layout.layout_bottom);
		View view = inflater.inflate(R.layout.layout_bottom, null);
		
		String str = getArguments().getString("info");
		Log.d("onCreateView", "receive msg:"+str);
		return view;
	}
}
