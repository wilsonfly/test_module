package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class RunningTimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);// ����ҳ�沼��
        startService(new Intent(this, RunningTimeService.class));// ��������
    }
}
