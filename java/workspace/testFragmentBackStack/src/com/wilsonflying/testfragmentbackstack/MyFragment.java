package com.wilsonflying.testfragmentbackstack;

import java.util.Random;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.layout_section, null);
		// View view = inflater.inflate(R.layout.layout_section, container,
		// false);

		// EditText et = (EditText) view.findViewById(R.id.et);
		// et.setText(String.valueOf( Math.abs(new Random().nextLong())));//刚开始实际测试效果是,多个页面显示内容叠加起来了！！！原因见layout_section.xml

		TextView tv = (TextView) view.findViewById(R.id.tv);
		tv.setText("" + Math.abs(new Random().nextLong()));
		return view;
	}
}
