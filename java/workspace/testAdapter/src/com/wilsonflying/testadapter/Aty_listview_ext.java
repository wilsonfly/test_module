package com.wilsonflying.testadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Aty_listview_ext extends Activity implements OnItemClickListener, OnItemLongClickListener {

	private ArrayAdapter<cellData_listview> adapter;
	private Button btn_add;
	private EditText et_name;
	private EditText et_sex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listview_ext);
		
		btn_add = (Button) findViewById(R.id.btn_add);
		et_name = (EditText) findViewById(R.id.et_name);
		et_sex = (EditText) findViewById(R.id.et_sex);
		
		adapter = new ArrayAdapter<cellData_listview>(Aty_listview_ext.this, android.R.layout.simple_list_item_1);

		adapter.add(new cellData_listview("李雷", "男", 20));
		adapter.add(new cellData_listview("韩梅梅", "女", 18));
		
		ListView lv = (ListView) findViewById(R.id.listview);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		
		findViewById(R.id.btn_add).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(et_name.getText()==null || et_sex.getText()==null){
					Toast.makeText(Aty_listview_ext.this, "填写完整信息先", Toast.LENGTH_SHORT).show();
				}else{
					adapter.add(new cellData_listview(et_name.getText().toString(), et_sex.getText().toString(), 88));
				}
					
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		cellData_listview data = adapter.getItem(position);
		Toast.makeText(Aty_listview_ext.this, ""+data.getName()+" "+data.getSex()+" "+data.getAge(), Toast.LENGTH_SHORT).show();
	}



	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		// TODO Auto-generated method stub
	new AlertDialog.Builder(Aty_listview_ext.this).setTitle("warning!").setMessage("是否确认删除").setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				adapter.remove(adapter.getItem(position));
			}
		}).setNegativeButton("取消", null).show();
		return false;
	}
	
	
}
