package org.kobehjk.net.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import kobehjk.org.demo.R;

/**
 * Created by hejk on 2017/11/1.
 */

public class LaunchActivity extends BaseCompatActivity{

    private static int WaitTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startJump();

            }
        },WaitTime);
    }

    public void startJump() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        LaunchActivity.this.finish();
    }

}
