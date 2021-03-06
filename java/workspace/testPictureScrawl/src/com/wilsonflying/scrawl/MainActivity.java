package com.wilsonflying.scrawl;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuInflater inflator = new MenuInflater(this);
//		inflator.inflate(R.menu.toolsmenu, menu);
		inflator.inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// return super.onOptionsItemSelected(item);

		MyDrawView dv = (MyDrawView) findViewById(R.id.myview);
		dv.paint.setXfermode(null);//取消擦除效果
		dv.paint.setStrokeWidth(1);
		switch (item.getItemId()) {
		case R.id.red:
			dv.paint.setColor(Color.RED);
			item.setChecked(true);
			break;
		case R.id.green:
			dv.paint.setColor(Color.GREEN);
			item.setChecked(true);
			break;
		case R.id.blue:
			dv.paint.setColor(Color.BLUE);
			item.setChecked(true);
			break;
		case R.id.width_1:
			dv.paint.setStrokeWidth(1);
			break;
		case R.id.width_2:
			dv.paint.setStrokeWidth(5);
			break;
		case R.id.width_3:
			dv.paint.setStrokeWidth(10);
			break;
		case R.id.clear:
			dv.clear();
			break;
		case R.id.save:
			dv.save();
			break;
		}
		return true;

	}
}
