package com.mingrisoft;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.widget.TextView;

public class RetrieveDataActivity extends Activity {
    private String[] columns = { Contacts._ID,// ϣ�����IDֵ
            Contacts.DISPLAY_NAME,// ϣ���������
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView) findViewById(R.id.result);// ��ò����ļ��еı�ǩ
        tv.setText(getQueryData());// Ϊ��ǩ��������
    }

    private String getQueryData() {
        StringBuilder sb = new StringBuilder();// ���ڱ����ַ���
        ContentResolver resolver = getContentResolver();// ���ContentResolver����
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, columns, null, null, null);// ��ѯ��¼
        int idIndex = cursor.getColumnIndex(columns[0]);// ���ID��¼������ֵ
        int displayNameIndex = cursor.getColumnIndex(columns[1]);// ���������¼������ֵ
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {// ����ȫ����¼
            int id = cursor.getInt(idIndex);
            String displayName = cursor.getString(displayNameIndex);
            sb.append(id + ": " + displayName + "\n");
        }
        cursor.close();// �ر�Cursor
        return sb.toString();// ���ز�ѯ���
    }
}