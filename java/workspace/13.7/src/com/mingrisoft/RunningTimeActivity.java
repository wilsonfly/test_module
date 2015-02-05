package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class RunningTimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);// 设置页面布局
        startService(new Intent(this, RunningTimeService.class));// 启动服务
    }
}
