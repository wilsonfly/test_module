package com.mingrisoft;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class ServicesListActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        StringBuilder serviceInfo = new StringBuilder();
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<RunningServiceInfo> services = manager.getRunningServices(100);// 获得正在运行的服务列表
        for (Iterator<RunningServiceInfo> it = services.iterator(); it.hasNext();) {
            RunningServiceInfo info = it.next();
            // 获得一个服务的详细信息并保存到StringBuilder
            serviceInfo.append("activeSince: " + formatData(info.activeSince) + "\n");
            serviceInfo.append("clientCount: " + info.clientCount + "\n");
            serviceInfo.append("clientLabel: " + info.clientLabel + "\n");
            serviceInfo.append("clientPackage: " + info.clientPackage + "\n");
            serviceInfo.append("crashCount: " + info.crashCount + "\n");
            serviceInfo.append("flags: " + info.flags + "\n");
            serviceInfo.append("foreground: " + info.foreground + "\n");
            serviceInfo.append("lastActivityTime: " + formatData(info.lastActivityTime) + "\n");
            serviceInfo.append("pid: " + info.pid + "\n");
            serviceInfo.append("process: " + info.process + "\n");
            serviceInfo.append("restarting: " + formatData(info.restarting) + "\n");
            serviceInfo.append("service: " + info.service + "\n");
            serviceInfo.append("started: " + info.started + "\n");
            serviceInfo.append("uid: " + info.uid + "\n");
            serviceInfo.append("\n");
        }

        ScrollView scrollView = new ScrollView(this);// 创建滚动视图
        TextView textView = new TextView(this);// 创建文本视图
        textView.setBackgroundColor(Color.BLACK);// 设置文本颜色
        textView.setTextSize(25);// 设置字体大小
        textView.setText(serviceInfo.toString());// 设置文本内容
        scrollView.addView(textView);// 将文本视图增加到滚动视图
        setContentView(scrollView);// 显示滚动视图
    }

    private static String formatData(long data) {// 用于格式化时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(data));
    }
}
