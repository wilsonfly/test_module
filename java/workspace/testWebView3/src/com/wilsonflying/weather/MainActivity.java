package com.wilsonflying.weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setWebChromeClient(new WebChromeClient());
		webview.setWebViewClient(new WebViewClient());
		webview.loadUrl("http://m.weather.com.cn/m/pn12/weather.htm ");
		webview.setInitialScale(57*4);//57是几个意思？
		
		Button button_bj = (Button) findViewById(R.id.button_bj); 
		Button button_sh = (Button) findViewById(R.id.button_sh); 
		Button button_gz = (Button) findViewById(R.id.button_gz); 
		button_bj.setOnClickListener(this);
		button_sh.setOnClickListener(this);
		button_gz.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_bj:
			openUrl("101010100T");
			break;
		case R.id.button_sh:
			openUrl("101020100T");
			break;
		case R.id.button_gz:
			openUrl("101280101T");
			break;
		default:
			break;
		}
	}
	
	private void openUrl(String id){
		webview.loadUrl("http://m.weather.com.cn/m/pn12/weather.htm?id="+id+" ");
	}
}
