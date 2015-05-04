package com.wilsonflying.testcontentresolver;


import com.wilsonflying.testcontentresolver.R;

import android.R.string;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
//import android.provider.Contacts;//导入这个包是错的
import android.provider.ContactsContract.Contacts;//应该导入此包，否则contacts内容对应不上或者找不到
import android.widget.TextView;

public class MainActivity extends Activity {

    private String[] columns = {Contacts._ID, Contacts.DISPLAY_NAME};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		TextView tv = (TextView) findViewById(R.id.text1);
		tv.setText(getQueryData());
		
		TextView tv2 = (TextView) findViewById(R.id.text2);
		tv2.setText(getQueryDataNo2());
	}

	private String getQueryData(){
		StringBuilder sb = new StringBuilder();
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(Contacts.CONTENT_URI, columns, null, null, null);
		
		int idIndex = cursor.getColumnIndex(columns[0]);
		int displayNameIndex = cursor.getColumnIndex(columns[1]);
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			int id = cursor.getInt(idIndex);
			String displayName = cursor.getString(displayNameIndex);
			
			sb.append("id:"+id + " displayname:"+displayName+"\n");
		}
		cursor.close();
		return sb.toString();
	}
	
	private String getQueryDataNo2(){
		StringBuilder sb = new StringBuilder();
		ContentResolver resolver = getContentResolver();
		//Cursor cursor = resolver.query(Contacts.CONTENT_URI, null, null, null, null);//未排序版本
		Cursor cursor = resolver.query(Contacts.CONTENT_URI, null, null, null, Contacts.DISPLAY_NAME+" DESC");//排序版本

		while(cursor.moveToNext()){
			int idIndex = cursor.getColumnIndex(Contacts._ID);
			int displayIndex = cursor.getColumnIndex(Contacts.DISPLAY_NAME);
			
			int id = cursor.getInt(idIndex);
			String displayName = cursor.getString(displayIndex);
			
			Cursor cursor_phone = resolver.query(Phone.CONTENT_URI, null, Phone.CONTACT_ID+"="+id, null, null);//留意此处有个+id
			while(cursor_phone.moveToNext()){
				int phoneNumberIndex = cursor_phone.getColumnIndex(Phone.NUMBER);
				String phoneNumber = cursor_phone.getString(phoneNumberIndex);
				sb.append("id:"+id+" displayName:"+displayName + " phoneNumber:"+phoneNumber+"\n");
			}
		}		
		cursor.close();
		return sb.toString();
	}
	

}