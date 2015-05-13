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
        library = GestureLibraries.fromRawResource(this, R.raw.gestures);// 加载手势文件
        if (!library.load()) {// 如果加载失败则退出
            finish();
        }
        GestureOverlayView gesture = (GestureOverlayView) findViewById(R.id.gestures);
        gesture.addOnGesturePerformedListener(this);// 增加事件监听器
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> gestures = library.recognize(gesture);// 获得全部预测结果
        int index = 0;// 保存当前预测的索引号
        double score = 0.0;// 保存当前预测的得分
        for (int i = 0; i < gestures.size(); i++) {//获得最佳匹配结果
            Prediction result = gestures.get(i);// 获得一个预测结果
            if (result.score > score) {
                index = i;
                score = result.score;
            }
        }
        Toast.makeText(this, gestures.get(index).name, Toast.LENGTH_LONG).show();
    }
}