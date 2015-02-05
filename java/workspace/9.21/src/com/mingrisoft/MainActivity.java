package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Animation flare=AnimationUtils.loadAnimation(this, R.anim.flare);	//��ȡ��͸���ȱ仯��������Դ
        final ImageView iv=(ImageView)findViewById(R.id.imageView1);		//��ȡҪӦ�ö���Ч����ImageView
        iv.setX(100);	//�������ǵ�X�����λ��
        iv.setY(50);	//�������ǵ�Y�����λ��
        iv.startAnimation(flare);	//Ӧ�ö���Ч��
    }
}