package com.wilsonflying.testdialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private boolean[] checkedItems;
	@Override
	public void onCreate(Bundle param) {
		super.onCreate(param);
		setContentView(R.layout.my_layout);

		// 显示带取消、中立和确定按钮的对话框
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {//添加View类限定，跟dialog的listenser冲突

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
						.create();
				dialog.setIcon(R.drawable.advise);
				dialog.setTitle("我的系统提示");
				dialog.setMessage("是否要退出？");

				Log.i("testdailog", "button1 listener");
				dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"不", new DialogInterface.OnClickListener() {//提示信息不提示import某个类或者方法的时候，直接添加类名称来限定，不依赖提示

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "您单击了取消按钮",
								Toast.LENGTH_SHORT).show();

					}
				});
				
				dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "竟然有中立按钮", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, "您单击了中立按钮",Toast.LENGTH_SHORT).show();
					}
				});

				dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, "您单击了确定按钮，即将退出！",Toast.LENGTH_SHORT).show();
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						finish();
					}
				});
				dialog.show();
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String[] items = new String[] { "item1", "item2", "item3", "item4","item5" };
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise);
				builder.setTitle("选择项");
				builder.setItems(items, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
					}
				});
				builder.create().show();
			}
			
		});
		
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String[] items = new String[] { "item1", "item2", "item3", "item4","item5" };
				Builder builder = new Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise);
				builder.setTitle("单选项");
				builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();

					}
				});
				builder.setPositiveButton("确定", null);
				builder.create().show();
			}
		});
		
		Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String[] items = new String[] { "item1", "item2", "item3", "item4","item5" };
				//Boolean[] checkedItems = new Boolean[]{true, false, true,false,false};//内部定义该变量会有报错：Cannot refer to a non-final variable checkedItems inside an inner class defined in a different method
				checkedItems = new boolean[]{true, false, true,false,false};

				Builder builder = new Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise);
				builder.setTitle("多选项");
				builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
						// TODO Auto-generated method stub
						checkedItems[which] = isChecked;
					}
				});	
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String result="";
						for(int i=0; i<items.length; i++){
							if(checkedItems[i]){
								result+=items[i]+" ";
							}
						}
						if(!"".equals(result)){
							Toast.makeText(MainActivity.this, "选中了这些："+result, Toast.LENGTH_SHORT).show();
						}
					}
				});
				builder.create().show();
			}
		});
	}
}
