package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        WebView webview=(WebView)findViewById(R.id.webView1);	//��ȡ���ֹ���������ӵ�WebView���
        webview.loadUrl("http://192.168.1.66:8081/bbs/");	//ָ��Ҫ���ص���ҳ
    }
}