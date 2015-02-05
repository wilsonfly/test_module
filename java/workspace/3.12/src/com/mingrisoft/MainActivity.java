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
				EditText nicknameET=(EditText)findViewById(R.id.nickname);	//获取会员昵称编辑框组件
				String nickname=nicknameET.getText().toString();			//获取输入的会员昵称
				EditText pwdET=(EditText)findViewById(R.id.pwd);	//获取密码编辑框组件
				String pwd=pwdET.getText().toString();				//获取输入的密码 
				EditText emailET=(EditText)findViewById(R.id.email);	//获取E-mail编辑框组件
				String email=emailET.getText().toString();   			//获取输入的E-mail地址     
				Log.i("编辑框的应用","会员昵称:"+nickname);
				Log.i("编辑框的应用","密码:"+pwd);
				Log.i("编辑框的应用","E-mail地址:"+email);				
			}
		});
    }
}