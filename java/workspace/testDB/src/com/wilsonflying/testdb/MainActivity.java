package com.wilsonflying.testdb;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity {

	private final String TAG = "testdb";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
 
    }

    public void onClickBtn(View view){
        switch (view.getId()) {
        case R.id.button0:
        	deleteDatabase("usrInfo.db");
        	break;
		case R.id.button1:
			useSQL();
			break;
		case R.id.button2:
			useContentValue();
			break;
		case R.id.button3:
			useSqliteOpenHelper();
			break;
		default:
			break;
		}
    }
    
    
    private void useSqliteOpenHelper() {
		// TODO Auto-generated method stub
		MySqliteOpenHelper helper = new MySqliteOpenHelper(MainActivity.this, "usrtb", null, 1);
//		SQLiteDatabase readableDatabase = helper.getReadableDatabase();
		SQLiteDatabase writableDatabase = helper.getWritableDatabase();
		queryTheDB2(writableDatabase);
	}

	private void useContentValue() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = openOrCreateDatabase("usrInfo.db", MODE_PRIVATE, null);
		db.execSQL("create table if not exists usrtb(_id integer primary key autoincrement, name text not null, age integer not null, sex text not null)");
		
		ContentValues values = new ContentValues();
		values.put("name", "xiaodou3");
		values.put("age", 20);
		values.put("sex", "女");
		db.insert("usrtb", null, values);
		
		values.clear();
		values.put("name", "xiaodou4");
		values.put("age", 21);
		values.put("sex", "女");
		db.insert("usrtb", null, values);
		
		queryTheDB(db);
		
		values.clear();
		values.put("age", 17);
		db.update("usrtb", values, "age>?",	new String[]{"20"});
		db.delete("usrtb", "name like ?", new String[]{"%2"});
		
		queryTheDB2(db);

		db.close();
	}



	public void useSQL(){
    	SQLiteDatabase db = openOrCreateDatabase("usrInfo.db", MODE_PRIVATE, null);
    	db.execSQL("create table if not exists usrtb(_id integer primary key autoincrement, name text not null, age integer not null, sex text not null)");
    	db.execSQL("insert into usrtb(name, age, sex) values('xiaodou', 18, '女')");
    	db.execSQL("insert into usrtb(name, age, sex) values('xiaodou2', 19, '女')");
    	
    	queryTheDB(db);
    	
    	db.close();
    }

	private void queryTheDB(SQLiteDatabase db) {
		// TODO Auto-generated method stub
    	Cursor c = db.rawQuery("select * from usrtb", null);
    	if(c!=null){
    		
    		while(c.moveToNext()){
    			Log.d(TAG, "_id:"+""+c.getInt(c.getColumnIndex("_id")));
    			Log.d(TAG, "name:"+""+c.getString(c.getColumnIndex("name")));
    			Log.d(TAG, "age:"+""+c.getInt(c.getColumnIndex("age")));
    			Log.d(TAG, "sex:"+""+c.getString(c.getColumnIndex("sex")));
    			Log.d(TAG, "==============================");
    		}
    		
    		c.close();
    	}
    	
	}

	private void queryTheDB2(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Cursor c = db.query("usrtb", null, "_id>?", new String[]{"0"}, null, null, "age");
		if( c!=null){
			String[] columns = c.getColumnNames();
			while (c.moveToNext()) {
				for (String columnName : columns) {
					Log.d(TAG, columnName+" :"+c.getString(c.getColumnIndex(columnName)));
				}
			}
			c.close();
		}
	}
	
 

}
