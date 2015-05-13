package com.wilsonflying.testnewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        Info info=new Info();	//ʵ����һ���������������Ϣ�Ķ���
		        
		        if("".equals(((EditText)findViewById(R.id.stature)).getText().toString())){
		        	Toast.makeText(MainActivity.this, "������������ߣ������ܼ��㣡", Toast.LENGTH_SHORT).show();
		        	return;
		        }
		        int stature=Integer.parseInt(((EditText)findViewById(R.id.stature)).getText().toString());
		        RadioGroup sex=(RadioGroup)findViewById(R.id.sex);	//��ȡ�����Ա�ĵ�ѡ��ť��
		        //��ȡ��ѡ��ť���ֵ
		        for(int i=0;i<sex.getChildCount();i++){
					RadioButton r=(RadioButton)sex.getChildAt(i);		//��������ֵ��ȡ��ѡ��ť
					if(r.isChecked()){							//�жϵ�ѡ��ť�Ƿ�ѡ��
						info.setSex(r.getText().toString());		//��ȡ��ѡ�еĵ�ѡ��ť��ֵ
						break;								//����forѭ��
					}
				}

		        info.setStature(stature);	//�������
		        Bundle bundle=new Bundle();	//ʵ����һ��Bundle����
		        bundle.putSerializable("info", info);	//������Ļ�����Ϣ���浽Bundle������
		        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
		        intent.putExtras(bundle);	//��bundle���浽Intent������
		        startActivity(intent);	//����intent��Ӧ��Activity				
			}
		});
    }
}