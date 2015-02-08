package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity_layout);// 设置页面布局
        Intent intent = getIntent();// 获得Intent
        String username = intent.getStringExtra("com.mingrisoft.USERNAME");// 获得用户输入的用户名
        String password = intent.getStringExtra("com.mingrisoft.PASSWORD");// 获得用户输入的密码
        TextView usernameTV = (TextView) findViewById(R.id.usr);// 获得第二个Activity的文本框控件
        TextView passwordTV = (TextView) findViewById(R.id.pwd);// 获得第二个Activity的文本框控件
        usernameTV.setText("用户名：" + username);// 设置文本框内容
        passwordTV.setText("密    码：" + password);// 设置文本框内容
    }
}
