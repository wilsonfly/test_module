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
		        Info info=new Info();	//实例化一个保存输入基本信息的对象
		        
		        if("".equals(((EditText)findViewById(R.id.stature)).getText().toString())){
		        	Toast.makeText(MainActivity.this, "请输入您的身高，否则不能计算！", Toast.LENGTH_SHORT).show();
		        	return;
		        }
		        int stature=Integer.parseInt(((EditText)findViewById(R.id.stature)).getText().toString());
		        RadioGroup sex=(RadioGroup)findViewById(R.id.sex);	//获取设置性别的单选按钮组
		        //获取单选按钮组的值
		        for(int i=0;i<sex.getChildCount();i++){
					RadioButton r=(RadioButton)sex.getChildAt(i);		//根据索引值获取单选按钮
					if(r.isChecked()){							//判断单选按钮是否被选中
						info.setSex(r.getText().toString());		//获取被选中的单选按钮的值
						break;								//跳出for循环
					}
				}

		        info.setStature(stature);	//设置身高
		        Bundle bundle=new Bundle();	//实例化一个Bundle对象
		        bundle.putSerializable("info", info);	//将输入的基本信息保存到Bundle对象中
		        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
		        intent.putExtras(bundle);	//将bundle保存到Intent对象中
		        startActivity(intent);	//启动intent对应的Activity				
			}
		});
    }
}