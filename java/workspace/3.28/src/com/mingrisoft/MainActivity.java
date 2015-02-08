package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
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
        final Button button=(Button)findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		Toast.makeText(MainActivity.this, "单击了按钮！", Toast.LENGTH_SHORT).show();
        		
        	}
        });
        CheckBox checkbox=(CheckBox)findViewById(R.id.checkBox1);
        checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        	
        	@Override
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        		if(isChecked){		//判断该复选按钮是否被选中
        			button.setEnabled(true);		//设置按钮可用
        		}else{
        			button.setEnabled(false);		//设置按钮不可用
        		}
        	}
        });

    }
}