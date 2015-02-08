package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends Activity {
	private TabHost tabHost;		//����TabHost����Ķ���
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tabHost=(TabHost)findViewById(android.R.id.tabhost);	//��ȡTabHost����
        tabHost.setup();	//��ʼ��TabHost���
        LayoutInflater inflater = LayoutInflater.from(this); 	// ������ʵ����һ��LayoutInflater����  
        inflater.inflate(R.layout.tab1, tabHost.getTabContentView());  
        inflater.inflate(R.layout.tab2, tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab01")
        		.setIndicator("δ������")
        		.setContent(R.id.LinearLayout01));   //��ӵ�һ����ǩҳ
        tabHost.addTab(tabHost.newTabSpec("tab02")
        		.setIndicator("�ѽ�����")
        		.setContent(R.id.FrameLayout02));  	//��ӵڶ�����ǩҳ
    }
}