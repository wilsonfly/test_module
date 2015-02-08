package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivity_layout);// 设置页面布局
        Button button = (Button) findViewById(R.id.button);// 通过ID值获得按钮对象
        button.setOnClickListener(new View.OnClickListener() {// 为按钮增加单击事件监听器

            public void onClick(View v) {
                Intent intent = new Intent();// 创建Intent对象
                intent.setAction(Intent.ACTION_VIEW);// 为Intent设置动作
                startActivity(intent);// 将Intent传递给Activity
            }
        });
    }
}
