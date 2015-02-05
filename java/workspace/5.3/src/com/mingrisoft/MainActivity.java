package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button submit=(Button)findViewById(R.id.submit);	//��ȡ�ύ��ť
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String user=((EditText)findViewById(R.id.user)).getText().toString();	//��ȡ������û�
				String pwd=((EditText)findViewById(R.id.pwd)).getText().toString();	//��ȡ���������
				String repwd=((EditText)findViewById(R.id.repwd)).getText().toString();	//��ȡ�����ȷ������
				String email=((EditText)findViewById(R.id.email)).getText().toString();	//��ȡ�����E-mail��ַ
				if(!"".equals(user) && !"".equals(pwd) && !"".equals(email)){
					
					if(!pwd.equals(repwd)){	//�ж���������������Ƿ�һ��
						Toast.makeText(MainActivity.this, "������������벻һ�£����������룡", Toast.LENGTH_LONG).show();
						((EditText)findViewById(R.id.pwd)).setText("");	//�������༭��
						((EditText)findViewById(R.id.repwd)).setText("");	//���ȷ������༭��
						((EditText)findViewById(R.id.pwd)).requestFocus();	//������༭���ý���
					}else{	//���������Ϣ���浽Bundle�У�������һ���µ�Activity��ʾ������û�ע����Ϣ
						Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
						Bundle bundle=new Bundle();	//������ʵ����һ��Bundle����
						bundle.putCharSequence("user", user);	//�����û���
						bundle.putCharSequence("pwd", pwd);	//��������
						bundle.putCharSequence("email", email);	//����E-mail��ַ
						intent.putExtras(bundle);	//��Bundle������ӵ�Intent������
						startActivity(intent);	//�����µ�Activity
						
					}
				}else{
					Toast.makeText(MainActivity.this, "�뽫ע����Ϣ����������", Toast.LENGTH_LONG).show();
				}
				
			}
		});
    }
}