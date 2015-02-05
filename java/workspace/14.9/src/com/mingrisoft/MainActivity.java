package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {
	private WebView webview;	//声明WebView组件的对象

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		webview = (WebView) findViewById(R.id.webView1); // 获取布局管理器中添加的WebView组件
		CheckBox check = (CheckBox) findViewById(R.id.checkBox1);	//获取布局管理器中添加的复选框组件
		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					webview.getSettings().setJavaScriptEnabled(true); // 设置JavaScript可用
					webview.setWebChromeClient(new WebChromeClient());
					webview.loadUrl("http://192.168.1.66:8081/bbs/allowJS.jsp"); // 指定要加载的网页
				}else{
					webview.loadUrl("http://192.168.1.66:8081/bbs/allowJS.jsp"); // 指定要加载的网页
				}
			}
		});
		webview.loadUrl("http://192.168.1.66:8081/bbs/allowJS.jsp"); // 指定要加载的网页

	}
}