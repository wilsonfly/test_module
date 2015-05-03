package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private WebView webView;	//声明WebView组件的对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		webView=(WebView)findViewById(R.id.webView1);	//获取WebView组件
		webView.getSettings().setJavaScriptEnabled(true);	//设置JavaScript可用
		webView.setWebChromeClient(new WebChromeClient());	//处理JavaScript对话框
		webView.setWebViewClient(new WebViewClient());	//处理各种通知和请求事件，如果不使用该句代码，将使用内置浏览器访问网页
		webView.loadUrl("http://m.weather.com.cn/m/pn12/weather.htm ");	//设置默认显示的天气预报信息
		webView.setInitialScale(57*4);	//放网页内容放大4倍
		Button bj=(Button)findViewById(R.id.bj);		//获取布局管理器中添加的“北京”按钮
		bj.setOnClickListener(this);
		Button sh=(Button)findViewById(R.id.sh);		//获取布局管理器中添加的“上海”按钮
		sh.setOnClickListener(this);		
		Button heb=(Button)findViewById(R.id.heb);		//获取布局管理器中添加的“哈尔滨”按钮
		heb.setOnClickListener(this);
		Button cc=(Button)findViewById(R.id.cc);		//获取布局管理器中添加的“长春”按钮
		cc.setOnClickListener(this);
		Button sy=(Button)findViewById(R.id.sy);		//获取布局管理器中添加的“沈阳”按钮
		sy.setOnClickListener(this);
		Button gz=(Button)findViewById(R.id.gz);		//获取布局管理器中添加的“广州”按钮
		gz.setOnClickListener(this);
	}
	@Override
	public void onClick(View view){
		switch(view.getId()){
		case R.id.bj:		//单击的是“北京”按钮
			openUrl("101010100T");
			break;
		case R.id.sh:		//单击的是“上海”按钮
			openUrl("101020100T");
			break;			
		case R.id.heb:		//单击的是“哈尔滨”按钮
			openUrl("101050101T");
			break;			
		case R.id.cc:		//单击的是“长春”按钮
			openUrl("101060101T");
			break;			
		case R.id.sy:		//单击的是“沈阳”按钮
			openUrl("101070101T");
			break;			
		case R.id.gz:		//单击的是“广州”按钮
			openUrl("101280101T");
			break;			
		}
	}
	//打开网页的方法
	private void openUrl(String id){
		webView.loadUrl("http://m.weather.com.cn/m/pn12/weather.htm?id="+id+" ");	//获取并显示天气预报信息
	}
}