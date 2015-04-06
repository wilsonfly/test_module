package com.wilsonflying.autocompletecontacts;

import com.wilsonflying.autocompletecontacts.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {

	private String[] columns = new String[]{Contacts._ID, Contacts.DISPLAY_NAME};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(Contacts.CONTENT_URI, columns, null, null, null);
		
		contactListAdapter adapter = new contactListAdapter(this, cursor);
		AutoCompleteTextView textview = (AutoCompleteTextView) findViewById(R.id.edit);
		textview.setAdapter(adapter);
	}
}
