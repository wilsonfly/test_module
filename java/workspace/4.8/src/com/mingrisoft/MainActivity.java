package com.mingrisoft;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	private int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09, R.drawable.img10, R.drawable.img11,
			R.drawable.img12, }; // ���岢��ʼ������ͼƬid������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Gallery gallery = (Gallery) findViewById(R.id.gallery1); // ��ȡGallery���
		/********************** ʹ��BaseAdapterָ��Ҫ��ʾ������ *****************************/
		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // ����ImageView�Ķ���
				if (convertView == null) {
					imageview = new ImageView(MainActivity.this); // ʵ����ImageView�Ķ���
					imageview.setScaleType(ImageView.ScaleType.FIT_XY); // �������ŷ�ʽ
					imageview
							.setLayoutParams(new Gallery.LayoutParams(180, 135));
					TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
					imageview.setBackgroundResource(typedArray.getResourceId(
							R.styleable.Gallery_android_galleryItemBackground,
							0));
					imageview.setPadding(5, 0, 5, 0); // ����ImageView���ڱ߾�
				} else {
					imageview = (ImageView) convertView;
				}
				imageview.setImageResource(imageId[position]); // ΪImageView����Ҫ��ʾ��ͼƬ
				return imageview; // ����ImageView
			}

			/*
			 * ���ܣ���õ�ǰѡ���ID (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getItemId(int)
			 */
			@Override
			public long getItemId(int position) {
				return position;
			}

			/*
			 * ���ܣ���õ�ǰѡ�� (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getItem(int)
			 */
			@Override
			public Object getItem(int position) {
				return position;
			}

			/*
			 * ������� (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getCount()
			 */
			@Override
			public int getCount() {
				return imageId.length;
			}
		};
		gallery.setAdapter(adapter); // ����������Gallery����
		/*********************************************************************************/
		gallery.setSelection(imageId.length / 2); // ���м��ͼƬѡ��
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(MainActivity.this,
						"��ѡ���˵�" + String.valueOf(position) + "��ͼƬ",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}