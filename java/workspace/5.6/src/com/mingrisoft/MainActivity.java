package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button=(Button)findViewById(R.id.button1);	//��ȡѡ��ͷ��ť
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        Intent intent=new Intent(MainActivity.this,HeadActivity.class);
		        startActivityForResult(intent, 0x11);	//����intent��Ӧ��Activity				
			}
		});
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0x11 && resultCode==0x11){	//�ж��Ƿ�Ϊ�������Ľ��
			Bundle bundle=data.getExtras();		//��ȡ���ݵ����ݰ�
			int imageId=bundle.getInt("imageId");	//��ȡѡ���ͷ��ID
			ImageView iv=(ImageView)findViewById(R.id.imageView1);	//��ȡ�����ļ������ӵ�ImageView���
			iv.setImageResource(imageId);	//��ʾѡ���ͷ��
		}
	}
    
}