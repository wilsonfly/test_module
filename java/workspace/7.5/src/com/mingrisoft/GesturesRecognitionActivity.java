package com.mingrisoft;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.os.Bundle;
import android.widget.Toast;

public class GesturesRecognitionActivity extends Activity implements OnGesturePerformedListener {

    private GestureLibrary library;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        library = GestureLibraries.fromRawResource(this, R.raw.gestures);// ���������ļ�
        if (!library.load()) {// �������ʧ�����˳�
            finish();
        }
        GestureOverlayView gesture = (GestureOverlayView) findViewById(R.id.gestures);
        gesture.addOnGesturePerformedListener(this);// �����¼�������
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> gestures = library.recognize(gesture);// ���ȫ��Ԥ����
        int index = 0;// ���浱ǰԤ���������
        double score = 0.0;// ���浱ǰԤ��ĵ÷�
        for (int i = 0; i < gestures.size(); i++) {//������ƥ����
            Prediction result = gestures.get(i);// ���һ��Ԥ����
            if (result.score > score) {
                index = i;
                score = result.score;
            }
        }
        Toast.makeText(this, gestures.get(index).name, Toast.LENGTH_LONG).show();
    }
}