package com.wilsonflying.testbuttons;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
//import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout);

		// radiobutton
		final RadioGroup sex = (RadioGroup) findViewById(R.id.radiogroup);
		final RadioButton radio_man = (RadioButton)findViewById(R.id.radiobutton1);
		
		sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

					// TODO Auto-generated method stub
					RadioButton button = (RadioButton) findViewById(checkedId);
					Toast.makeText(MainActivity.this, "选择的是" + button.getText(),
							Toast.LENGTH_SHORT).show();

					if(checkedId==radio_man.getId()){
						Log.i("testbuttons", "选择的是" + radio_man.getText());
					}
			}
		});
		
		Button button_radio = (Button) findViewById(R.id.button_radiobutton);
		button_radio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int i = 0; i < sex.getChildCount(); i++) {//需将sex定义为final
					RadioButton radiobutton = (RadioButton) sex.getChildAt(i);
					if (radiobutton.isChecked()) {
						Log.i("testbuttons", "即将提交选择：" + radiobutton.getText());
						Toast.makeText(MainActivity.this,
								"即将提交选择：" + radiobutton.getText(),
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		// checkbox
		final CheckBox button_sport = (CheckBox) findViewById(R.id.checkbox_sport);
		final CheckBox button_music = (CheckBox) findViewById(R.id.checkbox_music);
		final CheckBox button_painting = (CheckBox) findViewById(R.id.checkbox_pinting);
		button_sport.setOnCheckedChangeListener(checkbox_listener);
		button_music.setOnCheckedChangeListener(checkbox_listener);
		button_painting.setOnCheckedChangeListener(checkbox_listener);

		Button button_checkbox = (Button)findViewById(R.id.button_checkbox);
		button_checkbox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String hobit="";
				if(button_sport.isChecked())//需将button_sport定义为final
					hobit+=button_sport.getText().toString()+" ";
				if(button_music.isChecked())//需将button_sport定义为final
					hobit+=button_music.getText().toString()+" ";
				if(button_painting.isChecked())//需将button_sport定义为final
					hobit+=button_painting.getText().toString()+" ";
				Toast.makeText(MainActivity.this, hobit, Toast.LENGTH_SHORT).show();
			}
		});
	}

	// 此接口是onCreate 接口外边
	public void myclick(View v) {
		Toast.makeText(MainActivity.this, "点击了ImageButton", Toast.LENGTH_SHORT)
				.show();
	}

	public OnCheckedChangeListener checkbox_listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			if (isChecked)
				Toast.makeText(MainActivity.this, "选择的是：" + buttonView.getText(), Toast.LENGTH_SHORT).show();
		}
	};
	
}
