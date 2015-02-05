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
	private WebView webView;	//声明WebView组件的对象
	private EditText urlText;	//声明作为地址栏的EditText对象
	private Button goButton;	//声明GO按钮对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        urlText=(EditText)findViewById(R.id.editText_url);	//获取布局管理器中添加的地址栏
        goButton=(Button)findViewById(R.id.button_go);	//获取布局管理器中添加的“GO”按钮
		webView=(WebView)findViewById(R.id.webView1);	//获取WebView组件
		webView.getSettings().setJavaScriptEnabled(true);	//设置JavaScript可用
		webView.setWebChromeClient(new WebChromeClient());	//处理JavaScript对话框
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);

		webView.setWebViewClient(new WebViewClient());	//处理各种通知和请求事件，如果不使用该句代码，将使用内置浏览器访问网页
		//为地址栏添加键盘键被按下的事件监听器
		urlText.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode==KeyEvent.KEYCODE_ENTER){	//如果为回车键
					if(!"".equals(urlText.getText().toString())){
							openBrowser();	//打开浏览器
							return true;
					}else{
						showDialog();	//弹出提示对话框
					}
				}
				return false;
			}
		});
		//为“GO”按钮添加单击事件监听器
		goButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!"".equals(urlText.getText().toString())){
					openBrowser();
				}else{
					showDialog();	//弹出提示对话框
				}
				
			}
		});
	}
	//用于打开网页的方法
	private void openBrowser(){
		webView.loadUrl(urlText.getText().toString());
		Toast.makeText(this, "正在加载："+urlText.getText().toString(), Toast.LENGTH_SHORT).show();
	}
	//用于显示对话框的方法
	private void showDialog(){
		new AlertDialog.Builder(MainActivity.this)
		.setTitle("网页浏览器")
		.setMessage("请输入要访问的网址")
		.setPositiveButton("确定",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog,int which){
				Log.d("WebWiew","单击确定按钮");
			}

		}).show();
	}
}