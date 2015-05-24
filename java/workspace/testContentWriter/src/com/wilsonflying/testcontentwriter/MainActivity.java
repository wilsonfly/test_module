package com.wilsonflying.testcontentwriter;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.button_writedb).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues cv = new ContentValues();
				cv.put("name", "齐天大圣");
				getContentResolver().insert(myContentProvider.URI, cv);
				
				cv = new ContentValues();
				cv.put("name", "如来");
				getContentResolver().insert(myContentProvider.URI, cv);
				
				cv = new ContentValues();
				cv.put("name", "嫦娥");
				getContentResolver().insert(myContentProvider.URI, cv);
				
				Toast.makeText(MainActivity.this, "写入完成", Toast.LENGTH_SHORT).show();
			}
		});
    }


}
