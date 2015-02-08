package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button=(Button)findViewById(R.id.button1);	//获取选择头像按钮
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        Intent intent=new Intent(MainActivity.this,HeadActivity.class);
		        startActivityForResult(intent, 0x11);	//启动intent对应的Activity				
			}
		});
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0x11 && resultCode==0x11){	//判断是否为待处理的结果
			Bundle bundle=data.getExtras();		//获取传递的数据包
			String city=bundle.getString("city");	//获取选择的头像ID
			TextView tv=(TextView)findViewById(R.id.city);	//获取布局文件中添加的ImageView组件
			tv.setText(city);
		}
	}
    
}