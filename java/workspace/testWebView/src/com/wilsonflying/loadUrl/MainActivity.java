package com.wilsonflying.loadUrl;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		/*******************加载url部分*******************/
//		WebView webview = (WebView) findViewById(R.id.webview);
//		webview.loadUrl("http://www.baidu.com");
		/*******************加载url部分*******************/

		/*******************测试javascript部分*******************/
		final WebView webview = (WebView) findViewById(R.id.webview);
		CheckBox checkbox = (CheckBox) findViewById(R.id.checkbox);
		
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					webview.getSettings().setJavaScriptEnabled(true);
					webview.setWebChromeClient(new WebChromeClient());
					webview.loadUrl("http://www.chsi.com.cn/xlcx/bgcx.jsp");
				}
			}
		});
		webview.loadUrl("http://www.chsi.com.cn/xlcx/index.jsp");

		/*******************测试javascript部分*******************/

	}
}
