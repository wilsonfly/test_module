package com.wilsonflying.testdb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper {


	public MySqliteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	//首次创建数据库的时候自动调用，一般把建库和建表的操作放这
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists usrtb(_id integer primary key autoincrement, name text not null, age integer not null,sex text not null)");
		db.execSQL("insert into usrtb(name, age, sex) values('xiaodou5', 22, '女')");
	}

	//当数据库的版本发生变化的时候自动调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
