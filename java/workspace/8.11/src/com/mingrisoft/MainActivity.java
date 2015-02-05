package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button button1 = (Button) findViewById(R.id.button1); 	// ��ȡ�����ļ�����ӵ�button1
        Button button2 = (Button) findViewById(R.id.button2); 		// ��ȡ�����ļ�����ӵ�button2
     // Ϊ��ť��ӵ����¼�����
     button2.setOnClickListener(new OnClickListener() {
     	@Override
     	public void onClick(View v) {
     		if(button1.isEnabled()){
     			button1.setEnabled(false); 					// ��button1��Ϊ����
     			button1.setText("���ǲ����ð�ť"); 				// �ı䰴ť����ʾ������     			
     		}else{
     			
     			button1.setEnabled(true); 					// ��button1��Ϊ����
     			button1.setText("���ǿ��ð�ť"); 				// �ı䰴ť����ʾ������
     		}
     	}
     });

    }
}