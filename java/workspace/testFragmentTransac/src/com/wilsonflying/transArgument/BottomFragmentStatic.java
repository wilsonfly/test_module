package com.wilsonflying.transArgument;

import com.wilsonflying.transArgument.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BottomFragmentStatic extends Fragment {
	
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_static, null);
		final TextView tv = (TextView) view.findViewById(R.id.tvShowInfoStatic);
		view.findViewById(R.id.btnGetAgument).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv.setText(getDescription());
				Toast.makeText(getActivity(), "getdescription:"+getDescription(), Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}
	
//    public void onClickStatic(View v){
//		Toast.makeText(getActivity(), "getdescription:"+getDescription(), Toast.LENGTH_SHORT).show();
//    }
}
