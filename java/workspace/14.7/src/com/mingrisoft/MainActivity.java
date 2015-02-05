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
        WebView webview=(WebView)findViewById(R.id.webView1);	//获取布局管理器中添加的WebView组件
        webview.loadUrl("http://192.168.1.66:8081/bbs/");	//指定要加载的网页
    }
}