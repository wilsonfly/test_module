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
        setContentView(R.layout.main);// ����ҳ�沼��
        Button currentTime = (Button) findViewById(R.id.current_time);// ͨ��IDֵ��ð�ť����
        currentTime.setOnClickListener(new View.OnClickListener() {// Ϊ��ť���ӵ����¼�������
                    public void onClick(View v) {
                        startService(new Intent(CurrentTimeActivity.this, CurrentTimeService.class));// ��������
                    }
                });
    }
}
