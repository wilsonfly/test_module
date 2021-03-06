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
		//layout.setLayoutDirection(LayoutDirection.LTR);//针对布局本身而言，所在位置是偏左还是偏右
		//layout.setOrientation(LinearLayout.VERTICAL);//线性布局是按行排列其中内容还是按列排列其中内容
		layout.setOrientation(LinearLayout.HORIZONTAL);//线性布局是按行排列其中内容还是按列排列其中内容
		
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
