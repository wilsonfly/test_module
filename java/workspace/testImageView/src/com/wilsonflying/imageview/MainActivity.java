package com.wilsonflying.imageview;

import com.wilsonflying.testimageview.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private ImageView[] img = new ImageView[3];
	private int[] imgPath = new int[] { R.drawable.img001, R.drawable.img002,
			R.drawable.img003 };

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);

		LinearLayout layout = (LinearLayout) findViewById(R.id.main_layout);
		//layout.setLayoutDirection(LayoutDirection.LTR);//��Բ��ֱ������ԣ�����λ����ƫ����ƫ��
		//layout.setOrientation(LinearLayout.VERTICAL);//���Բ����ǰ��������������ݻ��ǰ���������������
		layout.setOrientation(LinearLayout.HORIZONTAL);//���Բ����ǰ��������������ݻ��ǰ���������������
		
		for (int i = 0; i < imgPath.length; i++) {
			img[i] = new ImageView(this);
			img[i].setImageResource(imgPath[i]);
			img[i].setPadding(5, 5, 5, 5);

			LayoutParams lparam = new LayoutParams(250, 150);
			img[i].setLayoutParams(lparam);
			img[i].setX(30*i);
			img[i].setY(200);
		
			layout.addView(img[i]);
		}

	}
}