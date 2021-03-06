package com.wilsonflying.testannotation;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DimensionRes;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.util.Log;
import android.widget.TextView;

@EActivity(R.layout.activity_second)
public class SecondAty extends Activity {

	private final String TAG = "SecondAty";
	
	//这个比较奇怪，StrngRes报错显示没有定义
	@org.androidannotations.annotations.res.StringRes(R.string.updated_text_content)
	String text_content;

	@DimensionRes(R.dimen.textsize)
	float text_size;
	
	@ViewById(R.id.tvSecondAty)
	TextView tvSecondAty;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(TAG, "SecondAty :"+Thread.currentThread().getId());
	}
	
	@Click(R.id.btnDoBackgroud)
	public void testBackground(){
		doSomethingBackgroud();
	}
	
	//这个碉堡啊！！！
	@Click(R.id.btnDoUiThread)
	public void testUIThread(){
		doSomethingUIThread();
	}
	
	@UiThread
	private void doSomethingUIThread() {
		// TODO Auto-generated method stub
		tvSecondAty.setText("Call UiThread 更新内容");
		tvSecondAty.setText(text_content);
		tvSecondAty.setTextSize(text_size);
	}

	@Background
	public void doSomethingBackgroud(){
		Log.d(TAG, "doSomethingBackgroud :"+Thread.currentThread().getId());
	}
}
