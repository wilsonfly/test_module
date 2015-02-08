package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivity_layout);// 设置页面布局
        Button ok = (Button) findViewById(R.id.ok);// 通过ID值获得按钮对象
        ok.setOnClickListener(new View.OnClickListener() {// 为按钮增加单击事件监听器

            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.username);// 获得输入用户名的控件
                EditText password = (EditText) findViewById(R.id.password);// 获得输入密码的控件
                Intent intent = new Intent();// 创建Intent对象
                intent.putExtra("com.mingrisoft.USERNAME", username.getText().toString());// 封装用户名信息
                intent.putExtra("com.mingrisoft.PASSWORD", password.getText().toString());// 封装密码信息
                intent.setClass(FirstActivity.this, SecondActivity.class);// 指定传递对象
                startActivity(intent);// 将Intent传递给Activity
            }
        });
    }
}
