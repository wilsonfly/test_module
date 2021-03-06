package com.wilsonflying.gestureguess;

import java.util.ArrayList;


import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.EditText;

public class NumberInputActivity extends Activity implements OnGesturePerformedListener {
    private GestureLibrary library;
    private EditText et;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        library = GestureLibraries.fromRawResource(this, R.raw.gestures);// 加载手势文件
        et = (EditText) findViewById(R.id.editText);
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
        for (int i = 0; i < gestures.size(); i++) {// 获得最佳匹配结果
            Prediction result = gestures.get(i);// 获得一个预测结果
            if (result.score > score) {
                index = i;
                score = result.score;
            }
        }
        String text = et.getText().toString();// 获得编辑框中已经包含的文本
        text += gestures.get(index).name;// 获得最佳匹配
        et.setText(text);// 更新编辑框
    }
}