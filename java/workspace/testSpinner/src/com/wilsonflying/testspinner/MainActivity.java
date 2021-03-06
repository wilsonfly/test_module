package com.wilsonflying.testspinner;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
		
		final Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		//适配器有什么用？？？？？？？？？？？？？？？？？？？
		//通过数组资源文件的方法：
		//ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.theStringArray, R.layout.my_layout);
		//通过字符串数组的方式：
		//String[] card = new String[]{"身份证	","学生证","军人证","工作证","其他"};
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_layout);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long id) {
				String result = parent.getItemAtPosition(pos).toString(); // 获取选择项的值
				Toast.makeText(MainActivity.this, "点击了："+result, Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,"选择的证件类型是：" + spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show(); 
			}
		});
		
	}
}
