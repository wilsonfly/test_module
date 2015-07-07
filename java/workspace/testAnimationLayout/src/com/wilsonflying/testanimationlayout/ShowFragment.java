package com.wilsonflying.testanimationlayout;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

public class ShowFragment extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_container);
		
		getFragmentManager().beginTransaction().add(R.id.fragContainer, new MyFragment()).commit();
	}
	
	public class MyFragment extends Fragment{
		
		private void MyFragment() {
			// TODO Auto-generated method stub

		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
//			return super.onCreateView(inflater, container, savedInstanceState);
			
//			LinearLayout view = (LinearLayout) inflater.inflate(R.layout.layout_fragment, container);
//这里用上述方法也就是没有将attachRoot置位false的方法解析到view 会有如下报错
//java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent 
			
			LinearLayout view = (LinearLayout) inflater.inflate(R.layout.layout_fragment, container, false);
			
			ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
			sa.setDuration(500);
			
			LayoutAnimationController lac = new LayoutAnimationController(sa);
			lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
			
			view.setLayoutAnimation(lac);//view 需要是个layout类型，如果是view类型不会有setlayoutAnimation方法
			
			return view;
		}
	}
}
