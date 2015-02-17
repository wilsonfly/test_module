package com.wilsonflying.testnewactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class HeadActivity extends Activity {

	public int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09 }; 							// ���岢��ʼ������ͷ��id������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.head);		//���ø�Activityʹ�õĲ���
		GridView gridview = (GridView) findViewById(R.id.gridView1); 			// ��ȡGridView���
		BaseAdapter adapter=new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview;							//����ImageView�Ķ���
				if(convertView==null){
					imageview=new ImageView(HeadActivity.this);		//ʵ����ImageView�Ķ���
					/*************����ͼ��Ŀ�Ⱥ͸߶�******************/
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(158);
					imageview.setMaxHeight(150);
					/**************************************************/
					imageview.setPadding(5, 5, 5, 5);				//����ImageView���ڱ߾�
				}else{
					imageview=(ImageView)convertView;
				}
				imageview.setImageResource(imageId[position]);		//ΪImageView����Ҫ��ʾ��ͼƬ
				return imageview;	//����ImageView
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
				return imageId.length;
			}
		};
		
		gridview.setAdapter(adapter); 									// ����������GridView����
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				Intent intent=getIntent();	//��ȡIntent����
				Bundle bundle=new Bundle();	//ʵ����Ҫ���ݵ����ݰ�
				bundle.putInt("imageId",imageId[position] );// ��ʾѡ�е�ͼƬ
				intent.putExtras(bundle);	//�����ݰ����浽intent��
				setResult(0x11,intent);	//���÷��صĽ���룬�����ص��ø�Activity��Activity
				finish();		//�رյ�ǰActivity
			}
		});
		
	}

}
