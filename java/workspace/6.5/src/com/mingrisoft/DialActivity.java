package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);// 设置页面布局
        EditText numberTV = (EditText) findViewById(R.id.editText);// 通过ID值获得文本框对象
        final String number = numberTV.getText().toString();// 获得用户输入的电话号码
        Button dial = (Button) findViewById(R.id.button);// 通过ID值获得按钮对象
        dial.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();// 创建Intent对象
                intent.setAction(Intent.ACTION_DIAL);// 为Intent设置动作
                intent.setData(Uri.parse("tel:" + number));// 为Intent设置数据
                startActivity(intent);// 将Intent传递给Activity
            }
        });
    }
}
