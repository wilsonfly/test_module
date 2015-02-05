package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);// ����ҳ�沼��
        Button home = (Button) findViewById(R.id.home_button);// ͨ��IDֵ��ð�ť����
        home.setOnClickListener(new View.OnClickListener() {// Ϊ��ť���ӵ����¼�������

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();// ����Intent����
                intent.setAction(Intent.ACTION_MAIN);// ����Intent����
                intent.addCategory(Intent.CATEGORY_HOME);// ����Intent����
                startActivity(intent);// ��Intent���ݸ�Activity
            }
        });
    }
}
