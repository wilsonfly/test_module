package com.wilsonflying.testreadwrite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_userInfo extends SQLiteOpenHelper {

	public DB_userInfo(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "db_userInfo", null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 db.execSQL("CREATE TABLE "+ TABLE_USERINFO +"(" 
//		db.execSQL("CREATE TABLE userInfo("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name TEXT DEFAULT \"\"," + "sex TEXT DEFAULT \"\")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	private static final String TABLE_USERINFO = "userInfo";

	public static String getTableNameUserinfo() {
		return TABLE_USERINFO;
	}
}
