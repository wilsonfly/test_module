package com.wilsonflying.testcontentreader;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	private Uri URI = Uri.parse("content://com.wilsonflying.testcontentwriter");
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Cursor c = getContentResolver().query(URI, null, null, null, null);
        c.moveToFirst();
        System.out.println("c.getcount:"+c.getCount());
        
        for(int i=0; i<c.getCount(); i++){
        	Toast.makeText(MainActivity.this, "name:"+c.getString(c.getColumnIndex("name")), Toast.LENGTH_SHORT).show();
        	c.moveToNext();
        }
    }


}
