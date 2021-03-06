package com.wilsonflying.testintentsenddata;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClickBtn(View view) {
		switch (view.getId()) {
		case R.id.btnSendText:
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_TEXT, "传送内容");
			intent.setType("text/plain");
			startActivity(intent);
			Log.d("shs", "id button1");
			break;
		case R.id.button2:
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
//			bitmap.compress(CompressFormat.JPEG, 100, stream);//这样显示出来的ImageView会有黑色背景
			bitmap.compress(CompressFormat.PNG, 100, stream);//这样显示出来的ImageView反而没有黑色背景
			byte[] byteArray = stream.toByteArray();
			
			
			Intent intent2 = new Intent();
			intent2.setAction(Intent.ACTION_SEND);
			intent2.putExtra(Intent.EXTRA_STREAM, byteArray);
			intent2.setType("image/jpeg");
			startActivity(intent2);

			break;


		}
	}
}
