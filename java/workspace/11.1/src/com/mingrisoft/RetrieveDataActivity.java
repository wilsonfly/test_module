package com.mingrisoft;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.TextView;

public class RetrieveDataActivity extends Activity {
    private String[] columns = { Contacts._ID,// 希望获得ID值
            Contacts.DISPLAY_NAME,// 希望获得姓名
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView) findViewById(R.id.result);// 获得布局文件中的标签
        tv.setText(getQueryData());// 为标签设置数据
    }

    private String getQueryData() {
        StringBuilder sb = new StringBuilder();// 用于保存字符串
        ContentResolver resolver = getContentResolver();// 获得ContentResolver对象
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, columns, null, null, null);// 查询记录
        int idIndex = cursor.getColumnIndex(columns[0]);// 获得ID记录的索引值
        int displayNameIndex = cursor.getColumnIndex(columns[1]);// 获得姓名记录的索引值
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {// 迭代全部记录
            int id = cursor.getInt(idIndex);
            String displayName = cursor.getString(displayNameIndex);
            sb.append(id + ": " + displayName + "\n");
        }
        cursor.close();// 关闭Cursor
        return sb.toString();// 返回查询结果
    }
}