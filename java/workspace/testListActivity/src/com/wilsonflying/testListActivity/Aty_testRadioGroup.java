package com.wilsonflying.testListActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class Aty_testRadioGroup extends Activity {

	private ProgressDialog progressdialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_aty_test_radiogroup);
		
		final RadioButton radioButton_good = (RadioButton) findViewById(R.id.button_good);
		findViewById(R.id.button_submit).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(radioButton_good.isChecked()){
					new AlertDialog.Builder(Aty_testRadioGroup.this).setTitle("判断结果选").setMessage("回答正确").setPositiveButton("关闭", null).show();
				}else{
//					new AlertDialog.Builder(Aty_testRadioGroup.this).setTitle("判断结果选").setMessage("那就是你的不对了。。。").setPositiveButton("关闭", null).show();

					progressdialog = ProgressDialog.show(Aty_testRadioGroup.this, "思考提示框", "我竟无言以对啊，让我歇会。。。");
					
					new Thread(){
						
						public void run() {
							try {
								Thread.sleep(4000);
								progressdialog.dismiss();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	
						};
					}.start();


				}
			}
		});
		
	}
}
