package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		/****************ͨ��ָ���������ķ�ʽΪѡ���б��ָ���б���********************/
//		����һ
//		String[] ctype=new String[]{"���֤","ѧ��֤","����֤"};
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ctype);
//		������
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//				this, R.array.ctype,android.R.layout.simple_dropdown_item_1line);	//����һ��������
//		
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Ϊ�����������б������ʱ��ѡ����ʽ
//		spinner.setAdapter(adapter); // ����������ѡ���б�����
		
		/***************************************************************************/		
		// Ϊѡ���б�����OnItemSelectedListener�¼�����
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long id) {
				String result = parent.getItemAtPosition(pos).toString(); // ��ȡѡ�����ֵ
				Log.i("Spinnerʾ��", result);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		Button button = (Button) findViewById(R.id.button1); // ��ȡ�ύ��ť
		// Ϊ�ύ��ť��ӵ����¼�����
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ȡѡ���֤�����Ͳ�ͨ����ʾ����ʾ
				Toast.makeText(MainActivity.this,
						"��ѡ���֤�������ǣ�" + spinner.getSelectedItem().toString(),
						Toast.LENGTH_SHORT).show(); // ��ʾ��ѡ�еĸ�ѡ��ť
			}
		});
	}
}