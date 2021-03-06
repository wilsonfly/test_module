package com.wilsonflying.testwidget;

import com.wilsonflying.testwidgt.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends Activity {

	private TabHost tb;
	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		
		//tb = (TabHost) findViewById(R.id.tabhost);
		tb = (TabHost) findViewById(android.R.id.tabhost);//需要是android.R.id.tabhost
		tb.setup();
		
		LayoutInflater li = LayoutInflater.from(this);
		li.inflate(R.layout.widget1, tb.getTabContentView());
		li.inflate(R.layout.widget2, tb.getTabContentView());

		tb.addTab(tb.newTabSpec("tab01").setIndicator("第一个widget").setContent(R.id.framelayout));
		tb.addTab(tb.newTabSpec("tab02").setIndicator("第二个widget").setContent(R.id.linearlayout));
		
	}
}
