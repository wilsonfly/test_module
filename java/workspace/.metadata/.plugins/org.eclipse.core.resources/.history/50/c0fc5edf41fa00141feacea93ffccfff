package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        Info info=new Info();	//实例化一个保存输入基本信息的对象
		        
		        if("".equals(((EditText)findViewById(R.id.birthday)).getText().toString())){
		        	Toast.makeText(MainActivity.this, "请输入您的阳历生日，否则不能计算！", Toast.LENGTH_SHORT).show();
		        	return;
		        }
		        String birthday=((EditText)findViewById(R.id.birthday)).getText().toString();
		        
		        info.setBirthday(birthday);	//设置生日
		        Bundle bundle=new Bundle();	//实例化一个Bundle对象
		        bundle.putSerializable("info", info);	//将输入的基本信息保存到Bundle对象中
		        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
		        intent.putExtras(bundle);	//将bundle保存到Intent对象中
		        startActivity(intent);	//启动intent对应的Activity
			}
		});
    }
}