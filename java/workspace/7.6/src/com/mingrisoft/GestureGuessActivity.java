package com.mingrisoft;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;

public class GestureGuessActivity extends Activity implements OnGesturePerformedListener {
    private GestureLibrary library;
    private TextView resultTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        library = GestureLibraries.fromRawResource(this, R.raw.gestures);// ���������ļ�
        resultTV = (TextView) findViewById(R.id.prediction);
        if (!library.load()) {// �������ʧ�����˳�
            finish();
        }
        GestureOverlayView gesture = (GestureOverlayView) findViewById(R.id.gestures);
        gesture.addOnGesturePerformedListener(this);// �����¼�������
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> gestures = library.recognize(gesture);// ���ȫ��Ԥ����
        Collections.sort(gestures, new Comparator<Prediction>() {// ��Ԥ������������

                    @Override
                    public int compare(Prediction lhs, Prediction rhs) {
                        return lhs.name.compareTo(rhs.name);// ʹ�ý����Ӧ���ַ���������
                    }
                });
        StringBuilder results = new StringBuilder();// ����ȫ�����
        NumberFormat formatter = new DecimalFormat("#00.00");// �����ʽ����ʽ
        for (int i = 0; i < gestures.size(); i++) {// ����ȫ�����
            Prediction result = gestures.get(i);
            results.append(result.name + ": " + formatter.format(result.score) + "\n");
        }
        resultTV.setText(results);// ��ʾ���
    }
}