package com.wilsonflying.testfragmentbackstack;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
//import android.support.v4.app.Fragment;

public class MainActivity extends Activity implements OnBackStackChangedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		NewFragment(false);
		
		onBackStackChanged();//初始化第一次启动首页的标题
	}

	private void NewFragment(boolean toAdd) {
		MyFragment fragment = new MyFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		trans.add(R.id.fragment_container, fragment);
		if(toAdd){
//			trans.addToBackStack(String.valueOf(getFragmentManager().getBackStackEntryCount() + 1));//no1
			trans.addToBackStack(null);//no2
		}
		trans.commit();
		manager.addOnBackStackChangedListener(this);
	}


	@Override
	public void onBackStackChanged() {
		// TODO Auto-generated method stub
		setTitle("当前第"+(getFragmentManager().getBackStackEntryCount()+1) + "页");
	}
	
	public void onClickPrePage(View v){
		FragmentManager manager = getFragmentManager();
		manager.popBackStack();
		//manager.popBackStackImmediate("1",FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}
	
	public void onClickNextPage(View v){
		NewFragment(true);
	}
}
