package com.mingrisoft;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class ContactListActivity extends ListActivity {
    private String[] columns = { Contacts._ID, Contacts.DISPLAY_NAME };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, columns, null, null, null);
        startManagingCursor(cursor);// ÍÐ¹ÜCursor
        ListAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, columns, new int[] { android.R.id.text1,
                android.R.id.text2 });
        setListAdapter(adapter);
    }
}