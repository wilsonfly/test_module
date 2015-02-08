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
        		Toast.makeText(MainActivity.this, "�����˰�ť��", Toast.LENGTH_SHORT).show();
        		
        	}
        });
        CheckBox checkbox=(CheckBox)findViewById(R.id.checkBox1);
        checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        	
        	@Override
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        		if(isChecked){		//�жϸø�ѡ��ť�Ƿ�ѡ��
        			button.setEnabled(true);		//���ð�ť����
        		}else{
        			button.setEnabled(false);		//���ð�ť������
        		}
        	}
        });

    }
}