package com.bwei.www.drycargo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bwei.www.drycargo.R;

import java.util.Timer;
import java.util.TimerTask;

public class AdActivity extends AppCompatActivity {

    private TextView time;
    private int i = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        getSupportActionBar().hide();
        initView();
//        daojishi();
        jishi();
    }

    private void jishi() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int i = 4;

            @Override
            public void run() {
                AdActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time.setText(--i + "S");
                        if (i == 1) {
                            timer.cancel();
                            Intent intent = new Intent(AdActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }, 1000, 1000);
    }

//    private void daojishi() {
//        new Thread() {
//            @Override
//            public void run() {
//                for (int j = 3; j >= 0; j--) {
//                    try {
//                        sleep(1000);
//                        i--;
//                        handler.sendEmptyMessage(i);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                super.run();
//            }
//        };
//    }
//
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            for (int j = msg.what; j >= 0; j--) {
//                time.setText(j + "S");
//                if (j == 1) {
//                    Intent intent = new Intent(AdActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
//                daojishi();
//            }
//        }
//    };


    private void initView() {
        time = (TextView) findViewById(R.id.time);
    }
}
