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
	private WebView webView;	//����WebView����Ķ���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		webView=(WebView)findViewById(R.id.webView1);	//��ȡWebView���
		webView.getSettings().setJavaScriptEnabled(true);	//����JavaScript����
		webView.setWebChromeClient(new WebChromeClient());	//����JavaScript�Ի���
		webView.setWebViewClient(new WebViewClient());	//�������֪ͨ�������¼��������ʹ�øþ���룬��ʹ�����������������ҳ
		webView.loadUrl("http://m.weather.com.cn/m/pn12/weather.htm ");	//����Ĭ����ʾ������Ԥ����Ϣ
		webView.setInitialScale(57*4);	//����ҳ���ݷŴ�4��
		Button bj=(Button)findViewById(R.id.bj);		//��ȡ���ֹ���������ӵġ���������ť
		bj.setOnClickListener(this);
		Button sh=(Button)findViewById(R.id.sh);		//��ȡ���ֹ���������ӵġ��Ϻ�����ť
		sh.setOnClickListener(this);		
		Button heb=(Button)findViewById(R.id.heb);		//��ȡ���ֹ���������ӵġ�����������ť
		heb.setOnClickListener(this);
		Button cc=(Button)findViewById(R.id.cc);		//��ȡ���ֹ���������ӵġ���������ť
		cc.setOnClickListener(this);
		Button sy=(Button)findViewById(R.id.sy);		//��ȡ���ֹ���������ӵġ���������ť
		sy.setOnClickListener(this);
		Button gz=(Button)findViewById(R.id.gz);		//��ȡ���ֹ���������ӵġ����ݡ���ť
		gz.setOnClickListener(this);
	}
	@Override
	public void onClick(View view){
		switch(view.getId()){
		case R.id.bj:		//�������ǡ���������ť
			openUrl("101010100T");
			break;
		case R.id.sh:		//�������ǡ��Ϻ�����ť
			openUrl("101020100T");
			break;			
		case R.id.heb:		//�������ǡ�����������ť
			openUrl("101050101T");
			break;			
		case R.id.cc:		//�������ǡ���������ť
			openUrl("101060101T");
			break;			
		case R.id.sy:		//�������ǡ���������ť
			openUrl("101070101T");
			break;			
		case R.id.gz:		//�������ǡ����ݡ���ť
			openUrl("101280101T");
			break;			
		}
	}
	//����ҳ�ķ���
	private void openUrl(String id){
		webView.loadUrl("http://m.weather.com.cn/m/pn12/weather.htm?id="+id+" ");	//��ȡ����ʾ����Ԥ����Ϣ
	}
}