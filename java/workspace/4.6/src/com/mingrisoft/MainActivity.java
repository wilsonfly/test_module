package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	private int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,
			R.drawable.img09 }; // ��������ʼ��һ������Ҫ��ʾͼ��ID������
	private int index = 0; // ��ǰ��ʾͼ�������
	private ImageSwitcher imageSwitcher; // ����һ��ͼ���л�������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
		imageSwitcher.setImageResource(imageId[index]);// ��ʾĬ�ϵ�ͼƬ
		Button up = (Button) findViewById(R.id.button1); // ��ȡ����һ�š���ť
		Button down = (Button) findViewById(R.id.button2); // ��ȡ����һ�š���ť
		up.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

					if (index > 0) {
						index--;
					} else {
						index = imageId.length - 1;
					}
				imageSwitcher.setImageResource(imageId[index]); // ��ʾ��ǰͼƬ
			}
		});
		down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (index < imageId.length - 1) {
					index++;
				} else {
					index = 0;
				}
				imageSwitcher.setImageResource(imageId[index]); // ��ʾ��ǰͼƬ
			}
		});	
//		down.setId(002); // Ϊ����һ�š���ť����ID����
//		up.setId(001); // Ϊ����һ�š���ť����ID����
//		// ʵ����һ��OnClickListener��Ķ��󣬲���дonClick()����
//		OnClickListener ctrlOnClickListener = new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (v.getId() == 001) { // �������һ�Ű�ť
//					if (index > 0) {
//						index--;
//					} else {
//						index = imageId.length - 1;
//					}
//				} else if (v.getId() == 002) { // �������һ�Ű�ť
//					if (index < imageId.length - 1) {
//						index++;
//					} else {
//						index = 0;
//					}
//				}
//				imageSwitcher.setImageResource(imageId[index]); // ��ʾ��ǰͼƬ
//			}
//		};
//		up.setOnClickListener(ctrlOnClickListener); // Ϊ��һ�Ű�ť��ӵ����¼�������
//		down.setOnClickListener(ctrlOnClickListener); // Ϊ��һ�Ű�ť��ӵ����¼�������
	}
}