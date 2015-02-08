package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ScreenTouchEventActivity extends Activity implements OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// 调用父类构造方法
        LinearLayout layout = new LinearLayout(this);// 定义线性布局
        layout.setOnTouchListener(this);// 设置触摸事件监听器
        layout.setBackgroundResource(R.drawable.background);// 设置背景图片
        setContentView(layout);// 使用布局
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(this, "发生触摸事件", Toast.LENGTH_LONG).show();
        return true;
    }

}
