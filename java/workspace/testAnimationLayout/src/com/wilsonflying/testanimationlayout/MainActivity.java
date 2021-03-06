package com.wilsonflying.testanimationlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view){
    	switch (view.getId()) {
		case R.id.btnShowListView:
			Intent intent = new Intent(MainActivity.this, ShowListViewAty.class);
			startActivity(intent);
			break;
		case R.id.btnShowListView2:
			Intent intent2 = new Intent(MainActivity.this, ShowListViewAty2.class);
			startActivity(intent2);
			break;
		case R.id.btnShowFragment:
			Intent intent3 = new Intent(MainActivity.this, ShowFragment.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
    }
}
