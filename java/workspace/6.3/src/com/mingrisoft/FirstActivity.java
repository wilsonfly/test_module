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
        setContentView(R.layout.firstactivity_layout);// ����ҳ�沼��
        Button button = (Button) findViewById(R.id.button);// ͨ��IDֵ��ð�ť����
        button.setOnClickListener(new View.OnClickListener() {// Ϊ��ť���ӵ����¼�������

            public void onClick(View v) {
                Intent intent = new Intent();// ����Intent����
                intent.setAction(Intent.ACTION_VIEW);// ΪIntent���ö���
                startActivity(intent);// ��Intent���ݸ�Activity
            }
        });
    }
}
