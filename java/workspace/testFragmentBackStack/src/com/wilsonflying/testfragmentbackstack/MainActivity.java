package com.wilsonflying.testfragmentbackstack;

import java.util.Random;

import android.R.bool;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
//import android.support.v4.app.Fragment;

public class MainActivity extends Activity implements OnBackStackChangedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		NewFragment(false);
	}

	private void NewFragment(boolean toAdd) {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		MyFragment fragment = new MyFragment();
		trans.add(R.id.fragment_container, fragment);
		if(toAdd){
			trans.addToBackStack(String.valueOf(getFragmentManager().getBackStackEntryCount() + 1));
		}
		trans.commit();
		manager.addOnBackStackChangedListener(this);
	}

	public class MyFragment extends Fragment {

		@Override
		@Nullable
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			// return super.onCreateView(inflater, container,
			// savedInstanceState);
			
			
//			View view = inflater.inflate(R.layout.layout_section, null);
			View view = inflater.inflate(R.layout.layout_section, container, false);
//			TextView tv = (TextView) view.findViewById(R.id.tv);
//			tv.setText("say something here:" + Math.random()*100);
			
			EditText et = (EditText) view.findViewById(R.id.et);
//			et.setText("say something here:" + Math.random()*100);
//			et.clearComposingText();
//			et.setText("");
			et.setText("say something here:" + Math.abs(new Random().nextLong()));//TODO:实际测试效果是,多个页面显示内容叠加起来了！！！
			return view;
		}
	}

	@Override
	public void onBackStackChanged() {
		// TODO Auto-generated method stub
		setTitle("当前第"+(getFragmentManager().getBackStackEntryCount()+1) + "页");
	}
	
	public void onClickPrePage(View v){
		FragmentManager manager = getFragmentManager();
		manager.popBackStack();
		//fragmentManager.popBackStackImmediate("1",FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}
	
	public void onClickNextPage(View v){
		NewFragment(true);
	}
}