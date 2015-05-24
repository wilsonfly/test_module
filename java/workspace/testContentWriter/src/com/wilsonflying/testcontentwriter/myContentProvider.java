package com.wilsonflying.testcontentwriter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class myContentProvider extends ContentProvider {

	SQLiteDatabase db;
	private final String TABLE_NAME = "userInfo";
	public static final Uri URI = Uri.parse("content://com.wilsonflying.testcontentwriter");
	
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
//		db = SQLiteDatabase.openOrCreateDatabase("myprovider2.db", null);
		db = getContext().openOrCreateDatabase("myprovider.db", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE userInfo(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL)");
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
		return c;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		db.insert(TABLE_NAME, "_id", values);//????
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
