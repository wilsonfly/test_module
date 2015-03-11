package com.wilsonflying.testnewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final int REQUESTCODE = 100;
	
	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		
		Button button = (Button) findViewById(R.id.button_open);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, NewActivity.class);
				startActivity(intent);
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button_open2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, AddviewActivity.class);
				startActivity(intent);
			}
		});
		
		//����Ϣ�������activity
		Button button_submit = (Button) findViewById(R.id.button_submit);
		button_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText et = (EditText) findViewById(R.id.edittext_username);
				Bundle bundle = new Bundle();
				//bundle.putString("username", et.getText().toString());
				String username = et.getText().toString();
				if(!"".equals(username)){
					Log.i("testnewactiviy", "username:"+username);
					bundle.putCharSequence("username", username);
					
					Intent intent = new Intent(MainActivity.this, RegistActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else{
					Toast.makeText(MainActivity.this, "������д��Ϣ", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		//�������Է��ص�activity
		Button button_submit_back = (Button) findViewById(R.id.button_submit_back);
		button_submit_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText et = (EditText) findViewById(R.id.edittext_username);
				Bundle bundle = new Bundle();
				//bundle.putString("username", et.getText().toString());
				String username = et.getText().toString();
				if(!"".equals(username)){
					Log.i("testnewactiviy", "username:"+username);
					bundle.putCharSequence("username", username);
					
					Intent intent = new Intent(MainActivity.this, RegsitActivityBack.class);
					intent.putExtras(bundle);
					startActivityForResult(intent, REQUESTCODE);
				}
				else{
					Toast.makeText(MainActivity.this, "������д��Ϣ", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		//��super����û��
		//�������Ҫ��ʲô����onActivityResult����Բ���д
		//super.onActivityResult(requestCode, resultCode, data);
		if( requestCode==REQUESTCODE && resultCode==requestCode){
			EditText et = (EditText) findViewById(R.id.edittext_username);
			et.setText("���˹���");
		}
	}
}