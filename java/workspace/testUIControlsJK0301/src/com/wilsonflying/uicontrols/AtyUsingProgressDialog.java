package com.wilsonflying.uicontrols;

import cn.eoe.uicontrols.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

public class AtyUsingProgressDialog extends Activity {
	
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_using_progressdialog);
		
		findViewById(R.id.btnShowProgressDialog).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				progressDialog = ProgressDialog.show(AtyUsingProgressDialog.this, "加载中", "正在加载，请稍候");
				
				new Thread(){
					
					public void run() {
						
						try {
							Thread.sleep(3000);
							
							progressDialog.dismiss();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					};
					
				}.start();
			}
		});
	}
}
