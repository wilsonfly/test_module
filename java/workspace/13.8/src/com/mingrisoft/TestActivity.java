package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button startService = (Button) findViewById(R.id.start_service_button);
        startService.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startService(new Intent(TestActivity.this, LifecycleService.class));
            }
        });
        Button stopService = (Button) findViewById(R.id.stop_service_button);
        stopService.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                stopService(new Intent(TestActivity.this, LifecycleService.class));
            }
        });
    }
}
