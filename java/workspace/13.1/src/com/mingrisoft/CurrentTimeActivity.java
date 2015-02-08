package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CurrentTimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);// 设置页面布局
        Button currentTime = (Button) findViewById(R.id.current_time);// 通过ID值获得按钮对象
        currentTime.setOnClickListener(new View.OnClickListener() {// 为按钮增加单击事件监听器
                    public void onClick(View v) {
                        startService(new Intent(CurrentTimeActivity.this, CurrentTimeService.class));// 启动服务
                    }
                });
    }
}
