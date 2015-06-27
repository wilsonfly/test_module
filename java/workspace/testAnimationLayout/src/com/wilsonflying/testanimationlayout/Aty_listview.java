package com.wilsonflying.testanimationlayout;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Aty_listview extends Activity {

	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listview);
		
		mListView = (ListView) findViewById(R.id.lv);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			list.add("内容"+i);
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Aty_listview.this, android.R.layout.simple_list_item_1, list);
		mListView.setAdapter(adapter);
		
//		LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(Aty_listview.this, R.anim.anim_scale));
		LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(Aty_listview.this, R.anim.anim_zoom_in));
		lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
		mListView.setLayoutAnimation(lac);
		mListView.startLayoutAnimation();
	}
}