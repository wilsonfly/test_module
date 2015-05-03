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
			R.drawable.shoe_sorry }; // 定义一个保存全部图片ID的数组
	private ImageView image1;		//ImageView组件1
	private ImageView image2;		//ImageView组件2
	private ImageView image3;		//ImageView组件3
	private TextView result;		//显示结果

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image1 = (ImageView) findViewById(R.id.imageView1);
		image2 = (ImageView) findViewById(R.id.imageView2);
		image3 = (ImageView) findViewById(R.id.imageView3);
		result = (TextView) findViewById(R.id.textView1);
		reset(); // 将鞋子的顺序打乱
		// 为第一只鞋子添加单击事件监听
		image1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isRight(v, 0); // 判断结果
			}
		});
		// 为第二只鞋子添加单击事件监听
		image2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isRight(v, 1); // 判断结果

			}
		});
		// 为第三只鞋子添加单击事件监听
		image3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isRight(v, 2); // 判断结果

			}
		});
		Button button = (Button) findViewById(R.id.button1); // 获取“再玩一次”按钮
		// 为“再玩一次”按钮添加事件监听器
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				reset();
				result.setText(R.string.title); // 将标题恢复为默认值
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
	 * 判断猜出的结果
	 * 
	 * @param v
	 * @param index
	 */
	private void isRight(View v, int index) {
		// 使用随机数组中图片资源ID设置每个ImageView
		image1.setImageDrawable(getResources().getDrawable(imageIds[0]));
		image2.setImageDrawable(getResources().getDrawable(imageIds[1]));
		image3.setImageDrawable(getResources().getDrawable(imageIds[2]));
		// 为每个ImageView设置半透明效果
		image1.setAlpha(100);
		image2.setAlpha(100);
		image3.setAlpha(100);
		ImageView v1 = (ImageView) v; // 获取被单击的图像视图
		v1.setAlpha(255); // 设置图像视图的透明度
		if (imageIds[index] == R.drawable.shoe_ok) { // 判断是否猜对
			result.setText("恭喜您，猜对了，祝你幸福！");
		} else {
			result.setText("很抱歉，猜错了，要不要再试一次？");
		}
	}

	/**
	 * 重新开始
	 */
	private void reset() {
		for (int i = 0; i < 3; i++) {
			int temp = imageIds[i];				//将数组元素i保存到临时变量中
			int index = (int) (Math.random() * 2);		//生成一个随机数
			imageIds[i] = imageIds[index];			//将随机数指定的数组元素的内容赋值给数组元素i
			imageIds[index] = temp;				//将临时变量的值赋值给随机数组指定的那个数组元素
		}
	}

}