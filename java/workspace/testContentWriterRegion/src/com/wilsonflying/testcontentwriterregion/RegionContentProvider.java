package com.wilsonflying.testcontentwriterregion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

public class RegionContentProvider extends ContentProvider {

	private static UriMatcher mUriMatcher;
	private static final String AUTHORITY = "com.wilsonflying.regioncontentprovider";
	private static final int CITIES = 1;
	private static final int CITY_CODE = 2;
	private static final int CITY_NAME = 3;
	private static final int CITIES_IN_PROVINCE = 4;
	private SQLiteDatabase db;

	static {
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		mUriMatcher.addURI(AUTHORITY, "cities", CITIES);
		mUriMatcher.addURI(AUTHORITY, "code/#", CITY_CODE);
		mUriMatcher.addURI(AUTHORITY, "name/*", CITY_NAME);
		mUriMatcher.addURI(AUTHORITY, "cities_in_province/*",
				CITIES_IN_PROVINCE);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
//		File file = getContext().getDatabasePath("myprovider.db");
		File dir = new File("/data/data/"+getContext().getPackageName()+"/databases/");
		
		if( !dir.exists() ){
			dir.mkdir();
		}
		
		File file = new File(dir.toString()+"/myprovider.db");
		if (file!=null && !file.exists()) {
			try {
				InputStream is = getContext().getResources().getAssets()
						.open("region.db");
				FileOutputStream fos = new FileOutputStream(file.toString());

				byte[] buffer = new byte[512];
				int count = 0;
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}

				fos.close();
				is.close();

				db = SQLiteDatabase.openOrCreateDatabase(file.toString(), null);
				System.out.println("write file:" + file.toString());
				Toast.makeText( getContext(), file.toString()+"写入成功", Toast.LENGTH_LONG).show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			Toast.makeText( getContext(), file.toString()+"已存在", Toast.LENGTH_SHORT).show();
		}

		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub

		Cursor cursor = null;
		switch (mUriMatcher.match(uri)) {
		case CITIES:
			cursor = db.query("v_cities_province", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case CITY_CODE:
			String cityCode = uri.getPathSegments().get(1);
			if( selection == null ){
				selection = "city_code='"+cityCode+"'";
			}else{
				selection += " and (city_code='" + cityCode + "')";
				cursor = db.query("t_cities", projection, selection, selectionArgs, null, null, sortOrder);
			}
			break;
		case CITY_NAME:
			String cityName = uri.getPathSegments().get(1);
			if(selection == null){
				selection = "city_name='" + cityName + "'";
			}else{
				selection += " and (city_name='" + cityName + "')";
			}
			cursor = db.query("t_cities", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case CITIES_IN_PROVINCE:
			String provinceName = uri.getPathSegments().get(1);
			if(selection == null){
				selection = "province_name='" + provinceName + "'";
			}else{
				selection += " and (province_name='" + provinceName + "')";
			}
			cursor = db.query("v_cities_province", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		default:
			throw new IllegalArgumentException("<"+uri+">" + "格式不正确");
		}

		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
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
