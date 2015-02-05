package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button login=(Button)findViewById(R.id.login);		//通过ID获取布局文件中添加的按钮
        login.setOnClickListener(new OnClickListener() {	//为按钮添加单击事件监听
			
			@Override
			public void onClick(View v) {
				Toast toast=Toast.makeText(MainActivity.this, "您单击了普通按钮", Toast.LENGTH_SHORT);
				toast.show();
				
			}
		});
    }
    public void myClick(View view){
    	Toast toast=Toast.makeText(MainActivity.this, "您单击了图片按钮", Toast.LENGTH_SHORT);
		toast.show();
    }
}