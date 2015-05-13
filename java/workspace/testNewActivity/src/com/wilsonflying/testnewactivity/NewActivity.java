package com.wilsonflying.testnewactivity;

import com.wilsonflying.testnewactivity.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NewActivity extends Activity {

	@Override
	public void onCreate(Bundle param){
		super.onCreate(param);
		setContentView(R.layout.layout_newactiviy);
		
		Button button = (Button) findViewById(R.id.button_quit);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
