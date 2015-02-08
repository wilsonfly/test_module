package com.mingrisoft;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/****************��������ΪListViewָ���б����������********************/
		String[] ctype=new String[]{"�龰ģʽ","����ģʽ","�ֻ�","�������"};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,ctype);
	
		/***************************************************************************/		
		setListAdapter(adapter); //���øô�������ʾ���б�
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
				String result = l.getItemAtPosition(position).toString(); // ��ȡѡ�����ֵ
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			}
	}
