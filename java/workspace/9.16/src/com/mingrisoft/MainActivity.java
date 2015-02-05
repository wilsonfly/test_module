package com.mingrisoft;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private boolean flag=true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout ll=(LinearLayout)findViewById(R.id.ll);	//��ȡ�����ļ�����ӵ����Բ��ֹ�����
        final AnimationDrawable anim=(AnimationDrawable)ll.getBackground();	//��ȡAnimationDrawable����
        //Ϊ���Բ��ֹ�������ӵ����¼�������
        ll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(flag){
					anim.start();	//��ʼ���Ŷ���
					flag=false;
				}else{
					anim.stop();	//ֹͣ���Ŷ���
					flag=true;
				}
			}
		});
    }

}