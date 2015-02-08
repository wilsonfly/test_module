package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        final CheckBox like1=(CheckBox)findViewById(R.id.like1);	//获取第1个复选按钮
        final CheckBox like2=(CheckBox)findViewById(R.id.like2);	//获取第2个复选按钮
        final CheckBox like3=(CheckBox)findViewById(R.id.like3); 	//获取第3个复选按钮
        like1.setOnCheckedChangeListener(checkBox_listener);		//为like1添加状态改变监听
        like2.setOnCheckedChangeListener(checkBox_listener);		//为like2添加状态改变监听
        like3.setOnCheckedChangeListener(checkBox_listener);		//为like3添加状态改变监听
        Button button = (Button) findViewById(R.id.button1);		//获取提交按钮
		//为提交按钮添加单击事件监听
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//通过for循环遍历单选按钮组
				String like="";				//保存选中的值
				if(like1.isChecked())			//当第一个复选按钮被选中
					like+=like1.getText().toString()+" ";
				if(like2.isChecked())			//当第一个复选按钮被选中
					like+=like2.getText().toString()+" ";
				if(like3.isChecked())			//当第一个复选按钮被选中
					like+=like3.getText().toString()+" ";
				Toast.makeText(MainActivity.this, like, Toast.LENGTH_SHORT).show();	//显示被选中的复选按钮

			}
		});        
    }
    //创建一个状态改变监听对象
    private OnCheckedChangeListener checkBox_listener=new OnCheckedChangeListener() {
    	@Override
    	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    		if(isChecked){		//判断复选按钮是否被选中
    			Log.i("复选按钮","选中了["+buttonView.getText().toString()+"]");
    		}
    	}
    };
}