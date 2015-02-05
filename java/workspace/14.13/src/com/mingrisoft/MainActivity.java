package com.mingrisoft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private WebView webView;	//����WebView����Ķ���
	private EditText urlText;	//������Ϊ��ַ����EditText����
	private Button goButton;	//����GO��ť����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        urlText=(EditText)findViewById(R.id.editText_url);	//��ȡ���ֹ���������ӵĵ�ַ��
        goButton=(Button)findViewById(R.id.button_go);	//��ȡ���ֹ���������ӵġ�GO����ť
		webView=(WebView)findViewById(R.id.webView1);	//��ȡWebView���
		webView.getSettings().setJavaScriptEnabled(true);	//����JavaScript����
		webView.setWebChromeClient(new WebChromeClient());	//����JavaScript�Ի���
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);

		webView.setWebViewClient(new WebViewClient());	//�������֪ͨ�������¼��������ʹ�øþ���룬��ʹ�����������������ҳ
		//Ϊ��ַ����Ӽ��̼������µ��¼�������
		urlText.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode==KeyEvent.KEYCODE_ENTER){	//���Ϊ�س���
					if(!"".equals(urlText.getText().toString())){
							openBrowser();	//�������
							return true;
					}else{
						showDialog();	//������ʾ�Ի���
					}
				}
				return false;
			}
		});
		//Ϊ��GO����ť��ӵ����¼�������
		goButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!"".equals(urlText.getText().toString())){
					openBrowser();
				}else{
					showDialog();	//������ʾ�Ի���
				}
				
			}
		});
	}
	//���ڴ���ҳ�ķ���
	private void openBrowser(){
		webView.loadUrl(urlText.getText().toString());
		Toast.makeText(this, "���ڼ��أ�"+urlText.getText().toString(), Toast.LENGTH_SHORT).show();
	}
	//������ʾ�Ի���ķ���
	private void showDialog(){
		new AlertDialog.Builder(MainActivity.this)
		.setTitle("��ҳ�����")
		.setMessage("������Ҫ���ʵ���ַ")
		.setPositiveButton("ȷ��",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int which){
				Log.d("WebWiew","����ȷ����ť");
			}

		}).show();
	}
}