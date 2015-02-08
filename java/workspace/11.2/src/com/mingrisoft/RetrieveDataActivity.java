package com.mingrisoft;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.TextView;

public class RetrieveDataActivity extends Activity {
    private String[] columns = { Contacts._ID,// ���IDֵ
            Contacts.DISPLAY_NAME,// �������
            Phone.NUMBER,// ��õ绰
            Phone.CONTACT_ID, };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView tv = (TextView) findViewById(R.id.result);// ��ò����ļ��еı�ǩ
        tv.setText(getQueryData());// Ϊ��ǩ��������
    }

    private String getQueryData() {

        StringBuilder sb = new StringBuilder();// ���ڱ����ַ���
        ContentResolver resolver = getContentResolver();// ���ContentResolver����
        Cursor cursor = resolver.query(Contacts.CONTENT_URI, null, null, null, null);// ��ѯ��¼
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(columns[0]);// ���IDֵ������
            int displayNameIndex = cursor.getColumnIndex(columns[1]);// �����������
            int id = cursor.getInt(idIndex);// ���id
            String displayName = cursor.getString(displayNameIndex);// �������
            Cursor phone = resolver.query(Phone.CONTENT_URI, null, columns[3] + "=" + id, null, null);
            while (phone.moveToNext()) {
                int phoneNumberIndex = phone.getColumnIndex(columns[2]);// ��õ绰����
                String phoneNumber = phone.getString(phoneNumberIndex);// ��õ绰
                sb.append(displayName + ": " + phoneNumber + "\n");// ��������
            }
        }
        cursor.close();// �ر��α�
        return sb.toString();
    }
}