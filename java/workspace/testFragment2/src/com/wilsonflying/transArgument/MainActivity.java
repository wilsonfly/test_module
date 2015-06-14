package com.wilsonflying.transArgument;

import com.wilsonflying.transArgument.R;

import android.support.v7.app.ActionBarActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    			
    public void onClick_showBottomFragment(View v){
    	BottomFragment fragment = new BottomFragment();
    	Bundle bundle = new Bundle();
    	bundle.putString("info", "Hello BottomFragment");
    	fragment.setArguments(bundle);
    	
    	FragmentManager manager = getFragmentManager();
    	FragmentTransaction trans = manager.beginTransaction();
    	trans.add(R.id.fragment_container, fragment, "bottom_fragment");
    	trans.commit();
    	
    	Toast.makeText(getApplicationContext(), "参数已发送", Toast.LENGTH_SHORT).show();
    }
    
    public void onClick_getArgument(View v){
    	String data = getFragmentManager().findFragmentByTag("bottom_fragment").getArguments().getString("info");
    	TextView tv = (TextView) findViewById(R.id.tv_showInfo);
    	tv.setText(data);
    }
}