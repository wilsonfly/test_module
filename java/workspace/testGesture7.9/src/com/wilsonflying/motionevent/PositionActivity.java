package com.wilsonflying.motionevent;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PositionActivity extends Activity implements OnTouchListener {

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
        float x = event.getX();// 获得触摸位置的X坐标
        float y = event.getY();// 获得触摸位置的Y坐标
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {// 如果发生触摸
            // 显示用户触摸位置的坐标
            Toast.makeText(this, "X=" + x + "\nY=" + y + "\n持续时间：" +(event.getEventTime()-event.getDownTime()), Toast.LENGTH_LONG).show();
        }
        return true;
    }

}