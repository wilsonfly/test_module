package com.mingrisoft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startService(new Intent(this, TimeService.class));
    }
}
