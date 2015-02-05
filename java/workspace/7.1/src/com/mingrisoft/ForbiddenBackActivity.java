package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class ForbiddenBackActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);// 设置页面布局
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;//屏蔽后退键
        }
        return super.onKeyDown(keyCode, event);
    }
}
