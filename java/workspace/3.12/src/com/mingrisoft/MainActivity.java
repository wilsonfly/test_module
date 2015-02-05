package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText nicknameET=(EditText)findViewById(R.id.nickname);	//��ȡ��Ա�ǳƱ༭�����
				String nickname=nicknameET.getText().toString();			//��ȡ����Ļ�Ա�ǳ�
				EditText pwdET=(EditText)findViewById(R.id.pwd);	//��ȡ����༭�����
				String pwd=pwdET.getText().toString();				//��ȡ��������� 
				EditText emailET=(EditText)findViewById(R.id.email);	//��ȡE-mail�༭�����
				String email=emailET.getText().toString();   			//��ȡ�����E-mail��ַ     
				Log.i("�༭���Ӧ��","��Ա�ǳ�:"+nickname);
				Log.i("�༭���Ӧ��","����:"+pwd);
				Log.i("�༭���Ӧ��","E-mail��ַ:"+email);				
			}
		});
    }
}