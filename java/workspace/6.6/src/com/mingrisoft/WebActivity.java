package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WebActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);// ����ҳ�沼��
        Button button = (Button) findViewById(R.id.button);// ͨ��IDֵ��ð�ť����
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();// ����Intent����
                intent.setAction(Intent.ACTION_VIEW);// ΪIntent���ö���
                intent.setData(Uri.parse("http://www.google.com.hk"));// ΪIntent��������
                startActivity(intent);// ��Intent���ݸ�Activity
            }
        });
    }
}
