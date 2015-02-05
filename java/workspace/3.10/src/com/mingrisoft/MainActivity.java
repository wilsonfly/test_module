package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       addClick();
    }
    public void addClick(){
        //为“进入”按钮添加单击事件监听
        ImageView img0=(ImageView)findViewById(R.id.imageButton0);
        img0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "进入游戏", Toast.LENGTH_SHORT).show();
			}
		});
        //为“设置”按钮添加单击事件监听
        ImageView img1=(ImageView)findViewById(R.id.imageButton1);
        img1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "游戏设置", Toast.LENGTH_SHORT).show();
			}
		});
        //为“退出”按钮添加单击事件监听
        ImageView img2=(ImageView)findViewById(R.id.imageButton2);
        img2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "退出游戏", Toast.LENGTH_SHORT).show();
			}
		});
        //为“帮助”按钮添加单击事件监听
        ImageView img3=(ImageView)findViewById(R.id.imageButton3);
        img3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "帮助", Toast.LENGTH_SHORT).show();
			}
		});
        //为“风云榜”按钮添加单击事件监听
        ImageView img4=(ImageView)findViewById(R.id.imageButton4);
        img4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "风云榜", Toast.LENGTH_SHORT).show();
			}
		});     	
    }
}