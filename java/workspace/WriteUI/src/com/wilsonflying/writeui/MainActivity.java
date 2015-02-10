package com.wilsonflying.writeui;

import android.R.color;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	public TextView text2;
	
	public void onCreate(Bundle param){
		super.onCreate(param);
		
		FrameLayout frameLayout = new FrameLayout(this);
		frameLayout.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.background_001));
		setContentView(frameLayout);
		
		TextView text1 = new TextView(this);
		text1.setText("�ô�������UIʾ��");
		text1.setTextColor(Color.rgb(1, 1, 1));
		text1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 24);//���ִ�С����λ����
		frameLayout.addView(text1);
		
		//TextView text2 = new TextView(this);
		text2 = new TextView(this);
		text2.setText("����������Ϸ");
		text2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 80);
		text2.setTextColor(Color.rgb(5, 5, 5));
		LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
		text2.setLayoutParams(params);
		
		text2.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(MainActivity.this).setTitle("������ʾ") 
				.setMessage("�Ƿ�ȷ��Ҫ���룿")
				.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Log.d("huasheng", "���ȷ��");
							}
						}).setNegativeButton("�˳�",
								new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										Log.d("huasheng", "����˳�");
										finish();
									}
								}).show();
			}
		});
		frameLayout.addView(text2);
	}
	
}