package com.wilsonflying.testintent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);

	}

	public void onClickStartActivity(View view) {
		Intent intent = new Intent();

		switch (view.getId()) {
		case R.id.button_home:
			intent.setAction(intent.ACTION_MAIN);
			intent.addCategory(intent.CATEGORY_HOME);
			startActivity(intent);
			break;

		case R.id.button_newactivity:
			intent.setAction(intent.ACTION_VIEW);
			startActivity(intent);
			break;
		case R.id.button_thirdactivity:
			intent.setAction("my_action");// 不需要指定完整的包名+类名即隐式intent。action可以自定义，不必包名+intent.action+类名
			startActivity(intent);
			break;
		case R.id.button_webactivity:
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://www.baidu.com"));
			startActivity(intent);
			break;
		case R.id.button_forthactivity:
			intent = new Intent(
					"com.wilsonflying.testintent.intent.action.ForthActivity");// 不需要指定完整的包名+类名即隐式intent。
			startActivity(intent);
			break;
		case R.id.button_fifthactivity:
			// 方法一：用context和目标类类名构造component
			// Intent intent = new Intent();
			// intent.setComponent(new ComponentName(MainActivity.this,
			// ForthActivity.class));

			// 方法二：直接在构造intent对象时候传入context和目标类类名
			intent = new Intent(MainActivity.this, ForthActivity.class);
			startActivity(intent);// 显示intent
			break;

		case R.id.btn_call:
			intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
			startActivity(intent);
			break;
		case R.id.btn_showdial:
			intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:10086"));
			startActivity(intent);
			break;
		case R.id.btn_settings:
//			intent = new Intent("android.settings.SETTINGS");
			intent = new Intent("android.settings.WIFI_SETTINGS");
			startActivity(intent);
			
			break;
//		case R.id.btn_call:
//			
//			break;
			
		default:
			break;
		}
	}
}
