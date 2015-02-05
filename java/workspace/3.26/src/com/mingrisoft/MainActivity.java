package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	int[] imageIds = new int[] { R.drawable.shoe_ok, R.drawable.shoe_sorry,
			R.drawable.shoe_sorry }; // ����һ������ȫ��ͼƬID������
	private ImageView image1;		//ImageView���1
	private ImageView image2;		//ImageView���2
	private ImageView image3;		//ImageView���3
	private TextView result;		//��ʾ���

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image1 = (ImageView) findViewById(R.id.imageView1);
		image2 = (ImageView) findViewById(R.id.imageView2);
		image3 = (ImageView) findViewById(R.id.imageView3);
		result = (TextView) findViewById(R.id.textView1);
		reset(); // ��Ь�ӵ�˳�����
		// Ϊ��һֻЬ����ӵ����¼�����
		image1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isRight(v, 0); // �жϽ��
			}
		});
		// Ϊ�ڶ�ֻЬ����ӵ����¼�����
		image2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isRight(v, 1); // �жϽ��

			}
		});
		// Ϊ����ֻЬ����ӵ����¼�����
		image3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isRight(v, 2); // �жϽ��

			}
		});
		Button button = (Button) findViewById(R.id.button1); // ��ȡ������һ�Ρ���ť
		// Ϊ������һ�Ρ���ť����¼�������
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				reset();
				result.setText(R.string.title); // ������ָ�ΪĬ��ֵ
				image1.setAlpha(255);
				image2.setAlpha(255);
				image3.setAlpha(255);
				image1.setImageDrawable(getResources().getDrawable(
						R.drawable.shoe_default));
				image2.setImageDrawable(getResources().getDrawable(
						R.drawable.shoe_default));
				image3.setImageDrawable(getResources().getDrawable(
						R.drawable.shoe_default));
			}
		});
	}

	/**
	 * �жϲ³��Ľ��
	 * 
	 * @param v
	 * @param index
	 */
	private void isRight(View v, int index) {
		// ʹ�����������ͼƬ��ԴID����ÿ��ImageView
		image1.setImageDrawable(getResources().getDrawable(imageIds[0]));
		image2.setImageDrawable(getResources().getDrawable(imageIds[1]));
		image3.setImageDrawable(getResources().getDrawable(imageIds[2]));
		// Ϊÿ��ImageView���ð�͸��Ч��
		image1.setAlpha(100);
		image2.setAlpha(100);
		image3.setAlpha(100);
		ImageView v1 = (ImageView) v; // ��ȡ��������ͼ����ͼ
		v1.setAlpha(255); // ����ͼ����ͼ��͸����
		if (imageIds[index] == R.drawable.shoe_ok) { // �ж��Ƿ�¶�
			result.setText("��ϲ�����¶��ˣ�ף���Ҹ���");
		} else {
			result.setText("�ܱ�Ǹ���´��ˣ�Ҫ��Ҫ����һ�Σ�");
		}
	}

	/**
	 * ���¿�ʼ
	 */
	private void reset() {
		for (int i = 0; i < 3; i++) {
			int temp = imageIds[i];				//������Ԫ��i���浽��ʱ������
			int index = (int) (Math.random() * 2);		//����һ�������
			imageIds[i] = imageIds[index];			//�������ָ��������Ԫ�ص����ݸ�ֵ������Ԫ��i
			imageIds[index] = temp;				//����ʱ������ֵ��ֵ���������ָ�����Ǹ�����Ԫ��
		}
	}

}