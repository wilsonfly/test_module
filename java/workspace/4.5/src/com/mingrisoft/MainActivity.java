package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends Activity {
	private TabHost tabHost;		//声明TabHost组件的对象
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tabHost=(TabHost)findViewById(android.R.id.tabhost);	//获取TabHost对象
        tabHost.setup();	//初始化TabHost组件
        LayoutInflater inflater = LayoutInflater.from(this); 	// 声明并实例化一个LayoutInflater对象  
        inflater.inflate(R.layout.tab1, tabHost.getTabContentView());  
        inflater.inflate(R.layout.tab2, tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab01")
        		.setIndicator("未接来电")
        		.setContent(R.id.LinearLayout01));   //添加第一个标签页
        tabHost.addTab(tabHost.newTabSpec("tab02")
        		.setIndicator("已接来电")
        		.setContent(R.id.FrameLayout02));  	//添加第二个标签页
    }
}