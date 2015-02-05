package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final ListView listView=(ListView)findViewById(R.id.listView1);
		listView.addHeaderView(line());		//����header view

		/****************��������ΪListViewָ���б����������********************/
//		����һ
//		String[] ctype=new String[]{"�龰ģʽ","����ģʽ","�ֻ�","�������"};
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,ctype);
//		������
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.ctype,android.R.layout.simple_list_item_checked);	//����һ��������
		
		/***************************************************************************/		
		listView.setAdapter(adapter); // ����������ListView����
		listView.addFooterView(line());		//����footer view
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int pos,
					long id) {
				String result = parent.getItemAtPosition(pos).toString(); // ��ȡѡ�����ֵ
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			}

		});
	}

	private View line() {
		ImageView image=new ImageView(this);	//����һ��ͼ����ͼ
		image.setImageResource(R.drawable.line1);	//����Ҫ��ʾ��ͼƬ
		return image;
	}
}