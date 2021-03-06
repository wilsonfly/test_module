package com.wilsonflying.browser;

import com.wilsonflying.browser.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);

		Button button_forward = (Button) findViewById(R.id.button_forward);
		Button button_back = (Button) findViewById(R.id.button_back);
		final EditText edit_url = (EditText) findViewById(R.id.edittext);
		Button button_go = (Button) findViewById(R.id.button_go);
		final WebView webview = (WebView) findViewById(R.id.webview);

		webview.getSettings().setJavaScriptEnabled(true);
		webview.setWebChromeClient(new WebChromeClient());//处理javascript对话框
		webview.setWebViewClient(new WebViewClient());//本apk打开网页，而不是内置浏览器
		
		button_go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!"".equals(edit_url.getText().toString())) {
					webview.loadUrl(edit_url.getText().toString());
					Toast.makeText(getApplicationContext(), "opening one url",
							Toast.LENGTH_SHORT).show();
				} else {
					showDialog();
				}
			}
		});
		
		button_forward.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webview.goForward();
			}
		});
		button_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webview.goBack();
			}
		});
	}

	private void showDialog() {
		Builder builder = new AlertDialog.Builder(getApplicationContext());
		builder.setTitle("请输入网页地址");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "需要去输入地址了。。。",
						Toast.LENGTH_SHORT).show();
			}
		}).show();

	}
}
