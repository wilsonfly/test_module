package com.wilsonflying.testautocompletetextview;

import com.wilsonflying.testautocompletetextview.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	 private static final String[] COUNTRIES = new String[] {
		 "auto", "autocomplete", "autocompletetext", "autocompletetextview", "autocompletexxxx"};
	 private  AutoCompleteTextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);	//��ȡ�Զ�����ı���
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, 
        							android.R.layout.simple_dropdown_item_1line,COUNTRIES); //����һ��ArrayAdapter������

        textView.setAdapter(adapter);	//Ϊ�Զ�����ı�������������
        Button button=(Button)findViewById(R.id.button1);	//��ȡ������ť
        //Ϊ������ť���ӵ����¼�����
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
    }
}