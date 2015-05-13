package com.wilsonflying.testcombinedlayout;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);
		addClick();
	}

	public void addClick() {
		ImageView image_in = (ImageView) findViewById(R.id.imagebutton_in);
		image_in.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "进入游戏", Toast.LENGTH_SHORT)
						.show();
			}
		});

		// int[] ids = new
		// int[]{R.id.imagebutton_setting,R.id.imagebutton_exit,R.id.imagebutton_help,R.id.imagebutton_board
		// };
		// ImageView[] images = new ImageView[4];
		// images[0] = (ImageView) findViewById(R.id.imagebutton_setting);
		// images[1] = (ImageView) findViewById(R.id.imagebutton_exit);
		// images[2] = (ImageView) findViewById(R.id.imagebutton_help);
		// images[3] = (ImageView) findViewById(R.id.imagebutton_board);
		// final String[] toastText = new String[]
		// {"游戏设置","退出游戏","帮助信息","排行榜信息"};
		//
		// for( int i=0;i < 4; i++){
		// images[i].setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Toast.makeText(MainActivity.this, toastText[i],
		// Toast.LENGTH_SHORT).show();//text 需要final类型参数，使用变量i在此报错
		// }
		// });
		// }

		ImageView image_setting = (ImageView) findViewById(R.id.imagebutton_setting);
		image_setting.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "游戏设置", Toast.LENGTH_SHORT)
						.show();
			}
		});

		ImageView image_exit = (ImageView) findViewById(R.id.imagebutton_exit);
		image_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "退出游戏", Toast.LENGTH_SHORT)
						.show();
			}
		});
		
		ImageView image_help = (ImageView) findViewById(R.id.imagebutton_help);
		image_help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "帮助信息", Toast.LENGTH_SHORT)
						.show();
			}
		});
		
		ImageView image_board = (ImageView) findViewById(R.id.imagebutton_board);
		image_board.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "排行榜信息", Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

}
