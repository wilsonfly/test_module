package com.wilsonflying.testcontentreaderregion;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends Activity {

	private static final String AUTHORITY = "com.wilsonflying.regioncontentprovider";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowAll(View view){
    	ContentResolver resolver = getContentResolver();
    	Uri uri = Uri.parse("content://com.wilsonflying.regioncontentprovider/cities");
    	Cursor cursor = resolver.query(uri, new String[]{ "city_code as _id", "city_name", "province_code" }, null, null, null);
    	SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, 
    			android.R.layout.simple_list_item_1, 
    			cursor, 
    			new String[]{"city_name"}, 
    			new int[]{android.R.id.text1});// text1
    	ListView lv = (ListView) findViewById(R.id.lv);
    	lv.setAdapter(adapter);
    }
 
    public void onClickShowShandong(View view){
    	
    }
}
