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
        StringBuilder sb=new StringBuilder();	//����һ���ַ�������������Ҫ��ʾ��HTML���ݷ����ڸù�������
        sb.append("<div>ѡ��ѡ�Ȼ�������ѡ���н���ѡ��</div>");
        sb.append("<ul>");
        sb.append("<li>�༭���ݣ��������ӡ��ƶ���ɾ�������ϵĿ�ݹ��ߡ�</li>");
        sb.append("<li>�������ݣ��������������ϵ�С���ߡ�</li>");
        sb.append("<li>��ʾ���ݣ�������ʾ�����ϵ�С���ߡ�</li>");
        sb.append("</ul>");
        webview.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);	//��������
        
    }
}