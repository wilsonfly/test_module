package com.mingrisoft;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);		//设置该Activity使用的布局
		TextView sex=(TextView)findViewById(R.id.sex);	//获取显示性别的文本框
		TextView stature=(TextView)findViewById(R.id.stature);	//获取显示身高的文本框
		TextView weight=(TextView)findViewById(R.id.weight);	//获取显示标准体重的文本框
		Intent intent=getIntent();	//获取Intent对象
		Bundle bundle=intent.getExtras();	//获取传递的数据包
		Info info=(Info)bundle.getSerializable("info");	//获取一个可序列化的info对象
		sex.setText("您是一位"+info.getSex()+"士");	//获取性别并显示到相应文本框中
		stature.setText("您的身高是"+info.getStature()+"厘米");	//获取身高并显示到相应文本框中
		
		weight.setText("您的标准体重是"+getWeight(info.getSex(),info.getStature())+"公斤");	//显示计算后的标准体重
	}
	/**
	 * 功能：计算标准体重
	 * @param sex
	 * @param stature
	 * @return
	 */
	private String getWeight(String sex,float stature){
		String weight="";	//保存体重
		NumberFormat format=new DecimalFormat();
		
		if(sex.equals("男")){	//计算男士标准体重
			weight=format.format((stature-80)*0.7);
		}else{		//计算女士标准体重
			weight=format.format((stature-70)*0.6);
		}
		return weight;
	}
}
