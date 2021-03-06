package com.wilsonflying.testtoast;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Organization;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public void method_normal(){
		Toast.makeText(MainActivity.this, "maketext()方式显示的提示框",
				Toast.LENGTH_SHORT).show();
	}
	
	public void method_withPicture(){
		LinearLayout ll = new LinearLayout(MainActivity.this);

		ImageView iv = new ImageView(MainActivity.this);
		iv.setImageResource(R.drawable.alerm);
		iv.setPadding(0, 0, 5, 0);
		ll.addView(iv);
		
		Toast toast = new Toast(MainActivity.this);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, -200, 0);

		TextView tv = new TextView(MainActivity.this);
		tv.setText("手动构造textview添加到布局中的方式显示的提示信息");
		ll.addView(tv);

		toast.setView(ll);
		toast.show();
	}
	
	public void method_withPicture_No2(){
		Toast toast = Toast.makeText(getApplicationContext(), "带图片的toast", Toast.LENGTH_SHORT);
		LinearLayout ll = (LinearLayout) toast.getView();
//		ll.setGravity(Gravity.HORIZONTAL_GRAVITY_MASK);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.alerm);
		
//		ll.addView(iv);//imageview会在text之后
		ll.addView(iv, 0);//index=0 imageview 放到第0位置，也就是第一部分。
		
		toast.setView(ll);
		toast.show();
	}
	
	public void method_NewDefined(){
		//inflater method1
//		LayoutInflater inflater = LayoutInflater.from(this);
		
		//inflater method2
		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE); 
		
		View toastView = inflater.inflate(R.layout.toast_layout, null);//实例化layout文件
		Toast toast = new Toast(this);
		toast.setView(toastView);
		toast.show();
	}
	
	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);

		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);

		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				method_normal();
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				method_withPicture();
			}
		});
		
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				method_withPicture_No2();
			}
		});
		
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				method_NewDefined();
			}
		});

	}
}
