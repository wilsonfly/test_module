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

    public void onClick(View view){
    	switch (view.getId()) {
		case R.id.btn_showlistview:
			Intent intent = new Intent(MainActivity.this, Aty_listview.class);
			startActivity(intent);
			break;

		default:
			break;
		}
    }
}
