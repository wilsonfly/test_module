package com.wilsonflying.testbuttons;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ToggleButton;
//import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

public class MainActivity extends Activity {

	private  CheckBox checkbox_showButton;
	private ImageButton imagebutton_enter;
	private Button button_tobeshow;

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
		final CheckBox checkbox_sport = (CheckBox) findViewById(R.id.checkbox_sport);
		final CheckBox checkbox_music = (CheckBox) findViewById(R.id.checkbox_music);
		final CheckBox checkbox_painting = (CheckBox) findViewById(R.id.checkbox_pinting);
		//final CheckBox checkbox_showButton = (CheckBox) findViewById(R.id.checkbox_showbutton);
		checkbox_showButton = (CheckBox) findViewById(R.id.checkbox_showbutton);
		imagebutton_enter = (ImageButton) findViewById(R.id.imagebutton_enter);
		button_tobeshow = (Button) findViewById(R.id.button_tobeshow);
		
		checkbox_sport.setOnCheckedChangeListener(checkbox_listener);
		checkbox_music.setOnCheckedChangeListener(checkbox_listener);
		checkbox_painting.setOnCheckedChangeListener(checkbox_listener);
		checkbox_showButton.setOnCheckedChangeListener(checkbox_listener);
		imagebutton_enter.setVisibility(View.INVISIBLE);

		
		Button button_checkbox = (Button)findViewById(R.id.button_checkbox);
		button_checkbox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String hobit="";
				if(checkbox_sport.isChecked())//需将checkbox_sport定义为final
					hobit+=checkbox_sport.getText().toString()+" ";
				if(checkbox_music.isChecked())//需将checkbox_sport定义为final
					hobit+=checkbox_music.getText().toString()+" ";
				if(checkbox_painting.isChecked())//需将checkbox_sport定义为final
					hobit+=checkbox_painting.getText().toString()+" ";
				Toast.makeText(MainActivity.this, hobit, Toast.LENGTH_SHORT).show();
			}
		});
		
		ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton1);
		tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "current status:"+isChecked, Toast.LENGTH_SHORT).show();
			}
		});
		
	}

	// 此接口是onCreate
	public void myclick(View v) {
		Toast.makeText(MainActivity.this, "点击了ImageButton", Toast.LENGTH_SHORT)
				.show();
	}

	public OnCheckedChangeListener checkbox_listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			if(buttonView.getId()==checkbox_showButton.getId()){
				if(isChecked){
					imagebutton_enter.setVisibility(View.VISIBLE);
					button_tobeshow.setEnabled(true);
				}
				else{
					imagebutton_enter.setVisibility(View.INVISIBLE);
					button_tobeshow.setEnabled(false);
				}
				imagebutton_enter.invalidate();
			}
			if (isChecked)
				Toast.makeText(MainActivity.this, "选择的是：" + buttonView.getText(), Toast.LENGTH_SHORT).show();
		}
	};
	
	public void fun_onclick(View v){
		Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
	}
	
	
	
}
