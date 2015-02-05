package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);// ����ҳ�沼��
        EditText numberTV = (EditText) findViewById(R.id.editText);// ͨ��IDֵ����ı������
        final String number = numberTV.getText().toString();// ����û�����ĵ绰����
        Button dial = (Button) findViewById(R.id.button);// ͨ��IDֵ��ð�ť����
        dial.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();// ����Intent����
                intent.setAction(Intent.ACTION_DIAL);// ΪIntent���ö���
                intent.setData(Uri.parse("tel:" + number));// ΪIntent��������
                startActivity(intent);// ��Intent���ݸ�Activity
            }
        });
    }
}
