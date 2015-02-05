package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {
	private WebView webview;	//����WebView����Ķ���

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		webview = (WebView) findViewById(R.id.webView1); // ��ȡ���ֹ���������ӵ�WebView���
		CheckBox check = (CheckBox) findViewById(R.id.checkBox1);	//��ȡ���ֹ���������ӵĸ�ѡ�����
		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					webview.getSettings().setJavaScriptEnabled(true); // ����JavaScript����
					webview.setWebChromeClient(new WebChromeClient());
					webview.loadUrl("http://192.168.1.66:8081/bbs/allowJS.jsp"); // ָ��Ҫ���ص���ҳ
				}else{
					webview.loadUrl("http://192.168.1.66:8081/bbs/allowJS.jsp"); // ָ��Ҫ���ص���ҳ
				}
			}
		});
		webview.loadUrl("http://192.168.1.66:8081/bbs/allowJS.jsp"); // ָ��Ҫ���ص���ҳ

	}
}