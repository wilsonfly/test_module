package com.wilsonflying.independentFragment;

import com.wilsonflying.independentFragment.TopFragment.onTopButtonClickLister;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity implements onTopButtonClickLister{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

	@Override
	public void onClickEvent(String info) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "info:"+info+"MainActivity will do something", Toast.LENGTH_SHORT).show();
		BottomFragment fragment = (BottomFragment) getFragmentManager().findFragmentById(R.id.fragment_bottom);
		fragment.updateText("update text from MainActivity");
	}

}
