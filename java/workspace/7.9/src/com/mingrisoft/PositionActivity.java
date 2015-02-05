package com.mingrisoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PositionActivity extends Activity implements OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// ���ø��๹�췽��
        LinearLayout layout = new LinearLayout(this);// �������Բ���
        layout.setOnTouchListener(this);// ���ô����¼�������
        layout.setBackgroundResource(R.drawable.background);// ���ñ���ͼƬ
        setContentView(layout);// ʹ�ò���
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();// ��ô���λ�õ�X����
        float y = event.getY();// ��ô���λ�õ�Y����
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {// �����������
            // ��ʾ�û�����λ�õ�����
            Toast.makeText(this, "X=" + x + "\nY=" + y, Toast.LENGTH_LONG).show();
        }
        return true;
    }

}