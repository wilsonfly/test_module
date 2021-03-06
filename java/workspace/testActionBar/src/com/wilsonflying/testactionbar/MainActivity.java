package com.wilsonflying.testactionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private Button btnShowActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);//需要在setcontent之前，否则报错
        setContentView(R.layout.activity_main);
        
        btnShowActionBar = (Button) findViewById(R.id.btn_showActionBar);
        btnShowActionBar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
////				if(getActionBar() == null){
////					return;
////				}
//				if(getActionBar().isShowing() == true){
////					getActionBar().hide();
//					getSupportActionBar().hide();
//					btnShowActionBar.setText("显示actionBar");
//				}else{
////					getActionBar().show();
//					getSupportActionBar().show();
//					btnShowActionBar.setText("隐藏actionBar");
//				}
				if(getSupportActionBar().isShowing() == true){
					getSupportActionBar().hide();
					btnShowActionBar.setText("隐藏actionbar");
				}else{
					getSupportActionBar().show();
					btnShowActionBar.setText("显示actionbar");
				}
					
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        
        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
