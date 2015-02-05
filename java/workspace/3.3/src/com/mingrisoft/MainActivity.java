package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
    private  ImageView[] img=new ImageView[4];		//声明一个保存ImageView组件的数组
    private int[] imagePath=new int[]{
    	R.drawable.img01,R.drawable.img04,R.drawable.img03,R.drawable.img02
    };	//声明并初始化一个保存访问图片的数组
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout layout=(LinearLayout)findViewById(R.id.layout);	//获取XML文件中定义的线性布局管理器
        for(int i=0;i<imagePath.length;i++){
        	img[i]=new ImageView(this);	//创建一个ImageView组件
        	img[i].setImageResource(imagePath[i]);		//为ImageView组件指定要显示的图片
        	img[i].setPadding(5, 5, 5, 5);	//设置ImageView组件的内边距
        	LayoutParams params=new LayoutParams(253,148);		//设置图片的宽度和高度
        	img[i].setLayoutParams(params);	//为ImageView组件设置布局参数
        	layout.addView(img[i]);	//将ImageView组件添加到布局管理器中
        }
    }
}