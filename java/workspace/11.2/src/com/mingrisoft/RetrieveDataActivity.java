package com.mingrisoft;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.TextView;

public class RetrieveDataActivity extends Activity {
    private String[] columns = { Contacts._ID,// 获得ID值
            Contacts.DISPLAY_NAME,// 获得姓名
            Phone.NUMBER,// 获得电话
            Phone.CONTACT_ID, };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView) findViewById(R.id.result);// 获得布局文件中的标签
        tv.setText(getQueryData());// 为标签设置数据
    }

    private String getQueryData() {

        StringBuilder sb = new StringBuilder();// 用于保存字符串
        ContentResolver resolver = getContentResolver();// 获得ContentResolver对象
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, null, null, null, null);// 查询记录
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(columns[0]);// 获得ID值的索引
            int displayNameIndex = cursor.getColumnIndex(columns[1]);// 获得姓名索引
            int id = cursor.getInt(idIndex);// 获得id
            String displayName = cursor.getString(displayNameIndex);// 获得名称
            Cursor phone = resolver.query(Phone.CONTENT_URI, null, columns[3] + "=" + id, null, null);
            while (phone.moveToNext()) {
                int phoneNumberIndex = phone.getColumnIndex(columns[2]);// 获得电话索引
                String phoneNumber = phone.getString(phoneNumberIndex);// 获得电话
                sb.append(displayName + ": " + phoneNumber + "\n");// 保存数据
            }
        }
        cursor.close();// 关闭游标
        return sb.toString();
    }
}