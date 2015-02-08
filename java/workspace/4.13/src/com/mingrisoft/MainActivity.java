package com.mingrisoft;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private boolean[] checkedItems;//记录各列表项的状态
	private String[] items;//各列表项要显示的内容
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button1 = (Button) findViewById(R.id.button1); // 获取“显示带取消、中立和确定按钮的对话框”按钮
		// 为“显示带取消、中立和确定按钮的对话框”按钮添加单击事件监听器
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
				alert.setIcon(R.drawable.advise);	//设置对话框的图标
				alert.setTitle("系统提示：");	//设置对话框的标题
				alert.setMessage("带取消、中立和确定按钮的对话框！");	//设置要显示的内容
				//添加取消按钮
				alert.setButton(DialogInterface.BUTTON_NEGATIVE,"取消", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "您单击了取消按钮",
								Toast.LENGTH_SHORT).show();

					}
				});
				//添加确定按钮
				alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "您单击了确定按钮",
								Toast.LENGTH_SHORT).show();

					}
				});
				alert.setButton(DialogInterface.BUTTON_NEUTRAL,"中立",new OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {}
				});//添加中立按钮
				alert.show(); // 创建对话框并显示
			}
		});
		
		// 带列表的对话框
		Button button2 = (Button) findViewById(R.id.button2); // 获取“显示带列表的对话框”按钮
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final String[] items = new String[] { "跑步", "羽毛球", "乒乓球", "网球",
						"体操" };
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise1);	//设置对话框的图标
				builder.setTitle("请选择你喜欢的运动项目：");	//设置对话框的标题
				//添加列表项
				builder.setItems(items, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this,
								"您选择了" + items[which], Toast.LENGTH_SHORT).show();

					}
				});
				builder.create().show(); // 创建对话框并显示
			}
		});
		//带多个单选列表和确定按钮的列表对话框
		Button button3 = (Button) findViewById(R.id.button3); // 获取“显示带单选列表项的对话框”按钮
		button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final String[] items = new String[] { "标准", "无声", "会议", "户外",
						"离线" };
				// 显示带单选列表项的对话框
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise2);	//设置对话框的图标
				builder.setTitle("请选择要使用的情景模式：");	//设置对话框的标题
				builder.setSingleChoiceItems(items, 0, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this,
								"您选择了" + items[which], Toast.LENGTH_SHORT).show();	//显示选择结果

					}
				});
				
				builder.setPositiveButton("确定", null);	//添加确定按钮
				builder.create().show(); // 创建对话框并显示
			}
		});
		//带多个多选列表和确定按钮的列表对话框
		Button button4 = (Button) findViewById(R.id.button4); // 获取“显示带多选列表项的对话框”按钮
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkedItems= new boolean[] { false, true, false,true, false };	//记录各列表项的状态
				items = new String[] { "植物大战僵尸", "愤怒的小鸟", "泡泡龙", "开心农场",
						"超级玛丽" };	//各列表项要显示的内容
				// 显示带单选列表项的对话框
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setIcon(R.drawable.advise2);	//设置对话框的图标
				builder.setTitle("请选择您喜爱的游戏：");	//设置对话框标题
				builder.setMultiChoiceItems(items, checkedItems,
						new OnMultiChoiceClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which, boolean isChecked) {
								checkedItems[which]=isChecked;	//改变被操作列表项的状态	
							}
						});
				//为对话框添加“确定”按钮
				builder.setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String result="";	//用于保存选择结果
						for(int i=0;i<checkedItems.length;i++){
							if(checkedItems[i]){	//当选项被选择时
								result+=items[i]+"、";	//将选项的内容添加到result中
							}
						}
						//当result不为空时，通过消息提示框显示选择的结果
						if(!"".equals(result)){	
							result=result.substring(0, result.length()-1);	//去掉最后面添加的“、”号
							Toast.makeText(MainActivity.this, "您选择了[ "+result+" ]", Toast.LENGTH_LONG).show();
						}
					}
				});
				builder.create().show(); // 创建对话框并显示
			}
		});
	}
}