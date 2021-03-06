package com.wilsonflying.testcontentresolver;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
//import android.provider.Contacts;//导入这个包是错的
//应该导入此包，否则contacts内容对应不上或者找不到

public class MainActivity extends Activity {

	private TextView tv;
	private final String TAG = "contentResolver";
    private String[] columns = {Contacts._ID, Contacts.DISPLAY_NAME};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		tv = (TextView) findViewById(R.id.text1);

	}

	public void onClickBtn(View view){
		switch (view.getId()) {
		case R.id.button1:
			tv.setText(getQueryData());
			break;
		case R.id.button2:
			tv.setText(getQueryDataNo2());
			break;
		case R.id.button3:
			insertOneContact();
			break;
		default:
			break;
		}
	}
	
	private void insertOneContact() {
		// TODO Auto-generated method stub
		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		Uri uri = cr.insert(RawContacts.CONTENT_URI, values);
		Long rawContactId = ContentUris.parseId(uri);
		
		//插入Name
		values.clear();
		values.put(StructuredName.RAW_CONTACT_ID, rawContactId);
		values.put(StructuredName.DISPLAY_NAME, "麦兜");
		values.put(StructuredName.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
		cr.insert(Data.CONTENT_URI, values);
		
		//插入Num
		values.clear();
		values.put(Phone.RAW_CONTACT_ID, rawContactId);
		values.put(Phone.NUMBER, "18611223344");
		values.put(Phone.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
		cr.insert(Data.CONTENT_URI, values);
		
		Log.d(TAG, "inserted");
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
				sb.append("id:"+id+"\ndisplayName:"+displayName + "\nphoneNumber:"+phoneNumber+"\n");
			}
		}		
		cursor.close();
		return sb.toString();
	}
	

}
