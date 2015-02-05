package com.mingrisoft;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	private int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09, R.drawable.img10, R.drawable.img11,
			R.drawable.img12, }; // ���岢��ʼ������ͼƬid������
	private ImageSwitcher imageSwitcher; // ����һ��ͼ���л�������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Gallery gallery = (Gallery) findViewById(R.id.gallery1); // ��ȡGallery���
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1); // ��ȡͼ���л���
		// ���ö���Ч��
				imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
						android.R.anim.fade_in)); // ���õ��붯��
				imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
						android.R.anim.fade_out)); // ���õ�������
				imageSwitcher.setFactory(new ViewFactory() {

					@Override
					public View makeView() {
						ImageView imageView = new ImageView(MainActivity.this); // ʵ����һ��ImageView��Ķ���
						imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // ���ñ����ݺ�Ⱦ�������ͼ��
						imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
								LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
						return imageView; // ����imageView����
					}

				});
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
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imageSwitcher.setImageResource(imageId[position]);// ��ʾѡ�е�ͼƬ
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
	}
}