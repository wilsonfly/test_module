package com.wilsonflying.gestureguess;

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
        library = GestureLibraries.fromRawResource(this, R.raw.gestures);// 加载手势文件
        resultTV = (TextView) findViewById(R.id.prediction);
        if (!library.load()) {// 如果加载失败则退出
            finish();
        }
        GestureOverlayView gesture = (GestureOverlayView) findViewById(R.id.gestures);
        gesture.addOnGesturePerformedListener(this);// 增加事件监听器
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> gestures = library.recognize(gesture);// 获得全部预测结果
        Collections.sort(gestures, new Comparator<Prediction>() {// 将预测结果进行排序

                    @Override
                    public int compare(Prediction lhs, Prediction rhs) {
                        return lhs.name.compareTo(rhs.name);// 使用结果对应的字符串来排序
                    }
                });
        StringBuilder results = new StringBuilder();// 保存全部结果
        NumberFormat formatter = new DecimalFormat("#00.00");// 定义格式化样式
        for (int i = 0; i < gestures.size(); i++) {// 遍历全部结果
            Prediction result = gestures.get(i);
            results.append(result.name + ": " + formatter.format(result.score) + "\n");
        }
        resultTV.setText(results);// 显示结果
    }
}