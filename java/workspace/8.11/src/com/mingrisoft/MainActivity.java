package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button button1 = (Button) findViewById(R.id.button1); 	// 获取布局文件中添加的button1
        Button button2 = (Button) findViewById(R.id.button2); 		// 获取布局文件中添加的button2
     // 为按钮添加单击事件监听
     button2.setOnClickListener(new OnClickListener() {
     	@Override
     	public void onClick(View v) {
     		if(button1.isEnabled()){
     			button1.setEnabled(false); 					// 让button1变为可用
     			button1.setText("我是不可用按钮"); 				// 改变按钮上显示的文字     			
     		}else{
     			
     			button1.setEnabled(true); 					// 让button1变为可用
     			button1.setText("我是可用按钮"); 				// 改变按钮上显示的文字
     		}
     	}
     });

    }
}