package com.wilsonflying.testreadwrite;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;

public class testDB extends ListActivity {

	private SimpleCursorAdapter adapter;
	private EditText editName;
	private EditText editSex;
	private Button button_submit;
	DB_userInfo db;
	SQLiteDatabase dbRead;
	SQLiteDatabase dbWrite;
	
	private OnClickListener buttonSubmitOnClicklistioner = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			
			ContentValues cv = new ContentValues();
			cv.put("name", editName.getText().toString());
			cv.put("sex", editSex.getText().toString());
			dbWrite.insert(db.getTableNameUserinfo(), null, cv);
			
			refreshListView();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test_db);

		editName = (EditText) findViewById(R.id.editName);
		editSex = (EditText) findViewById(R.id.editSex);
		button_submit = (Button) findViewById(R.id.button_submit);
		button_submit.setOnClickListener(buttonSubmitOnClicklistioner );
		
		db = new DB_userInfo(getApplicationContext(), null, null, 1);
		dbRead = db.getReadableDatabase();
		dbWrite  = db.getWritableDatabase();

		adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.layout_list_db_cell, null, new String[]{"name","sex"}, new int[]{R.id.tvName, R.id.tvSex});
		setListAdapter(adapter);
		
		refreshListView();
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
//				return false;
				
//	弹不出来,这里边已经是内部匿名类的命名空间 new AlertDialog.Builder(getApplicationContext()).setTitle("提醒").setMessage("是否确认要删除？").setNegativeButton("否", null).setPositiveButton("是", new DialogInterface.OnClickListener() {
				new AlertDialog.Builder(testDB.this).setTitle("提醒").setMessage("是否确认要删除？").setNegativeButton("否", null).setPositiveButton("是", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Cursor c = adapter.getCursor();
						c.moveToPosition(position);
						
						int itemId = c.getInt(c.getColumnIndex("_id"));
						dbWrite.delete(db.getTableNameUserinfo(), "_id=?", new String[]{itemId+""});
						
						refreshListView();
						
					}
				}).show();
				
				return true;
			}
		});
		
//		DB_userInfo db = new DB_userInfo(getApplicationContext(), null, null, 1);
//		SQLiteDatabase dbWrite  = db.getWritableDatabase();
//		ContentValues cv = new ContentValues();
//		cv.put("name", "小孙");
//		cv.put("sex", "男");
//		dbWrite.insert(db.getTableNameUserinfo(), null, cv);
//
//		cv.clear();
//		cv.put("name", "小华");
//		cv.put("sex", "女");
//		dbWrite.insert(db.getTableNameUserinfo(), null, cv);
	}
	
	public void refreshListView(){
		Cursor c = dbRead.query(db.getTableNameUserinfo(), null, null, null, null, null, null);
		adapter.changeCursor(c);
	}
	
}
