package com.wilsonflying.listcontacts;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

//public class MainActivity extends Activity {
public class MainActivity extends ListActivity {//继承了ListActivity

	private String[] columns = new String[]{Contacts._ID, Contacts.DISPLAY_NAME};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(Contacts.CONTENT_URI, columns, null, null, null);
		
		startManagingCursor(cursor);
		ListAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, columns, new int[]{android.R.id.text1, android.R.id.text2});
		setListAdapter(adapter);
	}
	
}
