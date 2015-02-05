package com.mingrisoft;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.AutoCompleteTextView;

public class AutoCompletionActivity extends Activity {
    private  String[] columns = new String[] { Contacts._ID, Contacts.DISPLAY_NAME };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, columns, null, null, null);
        ContactListAdapter adapter = new ContactListAdapter(this, cursor);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.edit);
        textView.setAdapter(adapter);
    }
}