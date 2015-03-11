package com.wilsonflying.testintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
    protected void onCreate(Bundle param) {
        super.onCreate(param);
        setContentView(R.layout.my_layout);
        
        Button button = (Button) findViewById(R.id.button_home);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(intent.ACTION_MAIN);
				intent.addCategory(intent.CATEGORY_HOME);
				startActivity(intent);
			}
		});
        
        Button button_newactivity = (Button) findViewById(R.id.button_home);
        button_newactivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(intent.ACTION_VIEW);
				startActivity(intent);
			}
		});
	}
}