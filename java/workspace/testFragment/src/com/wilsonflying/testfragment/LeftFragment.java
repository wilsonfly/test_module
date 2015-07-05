package com.wilsonflying.testfragment;

import java.io.IOException;
import java.io.InputStream;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftFragment extends Fragment implements OnItemClickListener {

	String[] movieInfo = new String[]{"灵魂战车2","变形金刚3:月黑之时","敢死队2"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.layout_left, null);
		ListView listView = (ListView) view.findViewById(R.id.listView);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,movieInfo);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		try {
			InputStream is = getResources().getAssets().open("detail"+position);
			byte[] data = new byte[256];
			int count = is.read(data);
			String data_s = new String(data, 0, count, "utf-8");
			
			TextView tv = (TextView) getActivity().findViewById(R.id.tv_rightFragment);
			if(tv == null){
				Intent intent = new Intent(getActivity(), DetailAty.class);
				intent.putExtra("detailInfo", data_s);
				startActivity(intent);
			}else{
				tv.setText(data_s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
