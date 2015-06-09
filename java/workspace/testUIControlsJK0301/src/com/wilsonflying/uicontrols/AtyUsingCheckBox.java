package com.wilsonflying.uicontrols;

import cn.eoe.uicontrols.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class AtyUsingCheckBox extends Activity {

	
	private CheckBox cbRB,cbHSR,cbHSPG,cbDoufu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_using_checkbox);
		
		cbDoufu = (CheckBox) findViewById(R.id.cbDoufu);
		cbHSPG = (CheckBox) findViewById(R.id.cbHSPG);
		cbHSR = (CheckBox) findViewById(R.id.cbHSR);
		cbRB = (CheckBox) findViewById(R.id.cbRB);
		
		findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String str = "中午要吃的东西有：\n";
				if (cbRB.isChecked()) {
					str+="肉饼\n";
				}
				if (cbHSR.isChecked()) {
					str+="红烧肉盖饭\n";
				}
				if (cbHSPG.isChecked()) {
					str+="红烧排骨盖饭";
				}
				if (cbDoufu.isChecked()) {
					str+="豆腐盖饭";
				}
				
				new AlertDialog.Builder(AtyUsingCheckBox.this).setTitle("结果").setMessage(str).setPositiveButton("关闭", null).show();
			}
		});
		
		
	}
}
