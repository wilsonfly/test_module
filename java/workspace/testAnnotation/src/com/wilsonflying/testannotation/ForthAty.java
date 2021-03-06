package com.wilsonflying.testannotation;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

@EActivity(R.layout.activity_forth)
public class ForthAty extends Activity {

	@ViewById(R.id.lvNum)
	ListView lvNum;
	
	@AfterViews
	public void initListView(){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			list.add("item"+i);
		}
		
		MyAdapter adapter = new MyAdapter(ForthAty.this, list);
		lvNum.setAdapter(adapter);
	}
	
	@ItemClick(R.id.lvNum)
	public void onItemClick(){// 在lvNum.setOnItemClickListener之后，此接口不再生效
		Toast.makeText(ForthAty.this, "clicked", Toast.LENGTH_SHORT).show();
	}
	
	@ItemLongClick(R.id.lvNum)
	public void onItemLongClick(){// 在lvNum.setOnItemClickListener之后，此接口不再生效
		Toast.makeText(ForthAty.this, "long clicked", Toast.LENGTH_SHORT).show();
	}
	@AfterViews
	public void setItemClickListener(){
		lvNum.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(ForthAty.this, "clicked:"+position, Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
//		lvNum.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				Toast.makeText(ForthAty.this, "clicked:"+position, Toast.LENGTH_SHORT).show();
//				
//			}
//		});
	}
}
