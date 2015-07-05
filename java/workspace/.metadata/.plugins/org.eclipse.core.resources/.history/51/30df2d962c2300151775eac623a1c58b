package com.wilsonflying.transArgument;

import com.wilsonflying.transArgument.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

		return view;
	}
	
    public void onClickStatic(View v){
		Toast.makeText(getActivity(), "getdescription:"+getDescription(), Toast.LENGTH_SHORT).show();
    }
}
