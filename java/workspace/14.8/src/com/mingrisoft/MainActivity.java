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
        StringBuilder sb=new StringBuilder();	//创建一个字符串构建器，将要显示的HTML内容放置在该构建器中
        sb.append("<div>选择选项，然后从以下选项中进行选择：</div>");
        sb.append("<ul>");
        sb.append("<li>编辑内容：用于增加、移动和删除桌面上的快捷工具。</li>");
        sb.append("<li>隐藏内容：用于隐藏桌面上的小工具。</li>");
        sb.append("<li>显示内容：用于显示桌面上的小工具。</li>");
        sb.append("</ul>");
        webview.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);	//加载数据
        
    }
}