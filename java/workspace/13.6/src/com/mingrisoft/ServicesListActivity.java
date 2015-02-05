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
        List<RunningServiceInfo> services = manager.getRunningServices(100);// ����������еķ����б�
        for (Iterator<RunningServiceInfo> it = services.iterator(); it.hasNext();) {
            RunningServiceInfo info = it.next();
            // ���һ���������ϸ��Ϣ�����浽StringBuilder
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

        ScrollView scrollView = new ScrollView(this);// ����������ͼ
        TextView textView = new TextView(this);// �����ı���ͼ
        textView.setBackgroundColor(Color.BLACK);// �����ı���ɫ
        textView.setTextSize(25);// ���������С
        textView.setText(serviceInfo.toString());// �����ı�����
        scrollView.addView(textView);// ���ı���ͼ���ӵ�������ͼ
        setContentView(scrollView);// ��ʾ������ͼ
    }

    private static String formatData(long data) {// ���ڸ�ʽ��ʱ��
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(data));
    }
}
