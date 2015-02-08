package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ButtonTouchEventActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);// 设置页面布局
        Button button = (Button) findViewById(R.id.button);// 获得按钮控件
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {// 处理用户短时间单击按钮事件
                Toast.makeText(ButtonTouchEventActivity.this, getText(R.string.short_click), Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {// 处理用户长时间单击按钮事件
                Toast.makeText(ButtonTouchEventActivity.this, getText(R.string.long_click), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}