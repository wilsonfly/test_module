package com.mingrisoft;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import android.widget.TextView;

public class ContactListAdapter extends CursorAdapter implements Filterable {

    private ContentResolver resolver;
    private  String[] columns = new String[] { Contacts._ID, Contacts.DISPLAY_NAME };
    public ContactListAdapter(Context context, Cursor c) {
        super(context, c);//调用父类构造方法
        resolver = context.getContentResolver();//初始化ContentResolver
    }

    @Override
    public void bindView(View arg0, Context arg1, Cursor arg2) {
        ((TextView) arg0).setText(arg2.getString(1));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        TextView view = (TextView) inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        view.setText(cursor.getString(1));
        return view;
    }

    @Override
    public CharSequence convertToString(Cursor cursor) {
        return cursor.getString(1);
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
        FilterQueryProvider filter = getFilterQueryProvider();
        if (filter != null) {
            return filter.runQuery(constraint);
        }

        Uri uri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI, Uri.encode(constraint.toString()));
        return resolver.query(uri, columns, null, null, null);
    }
}
