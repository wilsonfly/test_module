package com.wilsonflying.testcontentreaderregion;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


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
    	ContentResolver resolver = getContentResolver();
    	Uri uri = Uri.parse("content://com.wilsonflying.regioncontentprovider/cities_in_province/山东");
    	Cursor cursor = resolver.query(uri, new String[]{"city_code as _id", "city_name", "province_code"}, null, null, "city_code");
    	SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
    			new String[]{"city_name"},
    			new int[]{android.R.id.text1});
    	
    	ListView lv = (ListView) findViewById(R.id.lv);
    	lv.setAdapter(adapter);
    }
    public void onClickQueryWeifang(View view){
    	ContentResolver resolver = getContentResolver();
    	Uri uri = Uri.parse("content://com.wilsonflying.regioncontentprovider/name/潍坊");
    	Cursor cursor = resolver.query(uri, null, null, null, null);
    	if(cursor!=null && cursor.moveToFirst()){
    		Toast.makeText(this, "潍坊，city_code:"+ cursor.getString(cursor.getColumnIndex("city_code")), Toast.LENGTH_SHORT).show();
    	}
    	
    	//用code没有查到潍坊、沈阳
//    	uri = Uri.parse("content://com.wilsonflying.regioncontentprovider/code/0536");//024
//    	cursor = resolver.query(uri, null, null, null, null);
//    	if(cursor!=null && cursor.moveToFirst()){
//    		Toast.makeText(this, "0536,city_name:"+ cursor.getString(cursor.getColumnIndex("city_name")), Toast.LENGTH_SHORT).show();
//    	}
    	
    	
    }
    
    
}
