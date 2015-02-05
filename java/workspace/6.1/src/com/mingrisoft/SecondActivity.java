package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity_layout);// ����ҳ�沼��
        Intent intent = getIntent();// ���Intent
        String username = intent.getStringExtra("com.mingrisoft.USERNAME");// ����û�������û���
        String password = intent.getStringExtra("com.mingrisoft.PASSWORD");// ����û����������
        TextView usernameTV = (TextView) findViewById(R.id.usr);// ��õڶ���Activity���ı���ؼ�
        TextView passwordTV = (TextView) findViewById(R.id.pwd);// ��õڶ���Activity���ı���ؼ�
        usernameTV.setText("�û�����" + username);// �����ı�������
        passwordTV.setText("��    �룺" + password);// �����ı�������
    }
}
