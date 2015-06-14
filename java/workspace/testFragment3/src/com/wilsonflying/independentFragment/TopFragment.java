package com.wilsonflying.independentFragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class TopFragment extends Fragment implements OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.layout_topfragment, null);
//		view.setOnClickListener(this);
	
		view.findViewById(R.id.btn_topfragment).setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(listener != null){
			listener.onClick("Info From TopFragment");
		}
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		if( activity instanceof onTopButtonClickLister){
			listener = (onTopButtonClickLister) activity;
		}
		
		super.onAttach(activity);
	}
	
	public interface onTopButtonClickLister{
		public void onClick(String info);
	}
	private onTopButtonClickLister listener;
	
}
