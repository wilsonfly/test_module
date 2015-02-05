package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class HeadActivity extends Activity {

	public String[] city = new String[] { "����", "�Ϻ�","����","����","����","������","���","����","����","����","�Ͼ�","����" }; 							// ���岢��ʼ������ͷ��id������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.head);		//���ø�Activityʹ�õĲ���
		GridView gridview = (GridView) findViewById(R.id.gridView1); 			// ��ȡGridView���
		BaseAdapter adapter=new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView tv;							//����ImageView�Ķ���
				if(convertView==null){
					tv=new TextView(HeadActivity.this);		//ʵ����ImageView�Ķ���
//					/*************����ͼ��Ŀ�Ⱥ͸߶�******************/
//					imageview.setAdjustViewBounds(true);
//					imageview.setMaxWidth(158);
//					imageview.setMaxHeight(150);
//					/**************************************************/
					tv.setPadding(5, 5, 5, 5);				//����ImageView���ڱ߾�
				}else{
					tv=(TextView)convertView;
				}
				tv.setText(city[position]);		//ΪImageView����Ҫ��ʾ��ͼƬ
				return tv;	//����ImageView
			}
			/* 
			 * ���ܣ���õ�ǰѡ���ID
			 */
			@Override
			public long getItemId(int position) {
				return position;
			}
			/* 
			 * ���ܣ���õ�ǰѡ��
			 */
			@Override
			public Object getItem(int position) {
				return position;
			}
			/*
			 * �������
			 */
			@Override
			public int getCount() {
				return city.length;
			}
		};
		
		gridview.setAdapter(adapter); 									// ����������GridView����
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				Intent intent=getIntent();	//��ȡIntent����
				Bundle bundle=new Bundle();	//ʵ����Ҫ���ݵ����ݰ�
				bundle.putString("city",city[position] );// ��ʾѡ�е�ͼƬ
				intent.putExtras(bundle);	//�����ݰ����浽intent��
				setResult(0x11,intent);	//���÷��صĽ���룬�����ص��ø�Activity��Activity
				finish();		//�رյ�ǰActivity
			}
		});
		
	}

}
