package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivity_layout);// ����ҳ�沼��
        Button ok = (Button) findViewById(R.id.ok);// ͨ��IDֵ��ð�ť����
        ok.setOnClickListener(new View.OnClickListener() {// Ϊ��ť���ӵ����¼�������

            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.username);// ��������û����Ŀؼ�
                EditText password = (EditText) findViewById(R.id.password);// �����������Ŀؼ�
                Intent intent = new Intent();// ����Intent����
                intent.putExtra("com.mingrisoft.USERNAME", username.getText().toString());// ��װ�û�����Ϣ
                intent.putExtra("com.mingrisoft.PASSWORD", password.getText().toString());// ��װ������Ϣ
                intent.setClass(FirstActivity.this, SecondActivity.class);// ָ�����ݶ���
                startActivity(intent);// ��Intent���ݸ�Activity
            }
        });
    }
}
