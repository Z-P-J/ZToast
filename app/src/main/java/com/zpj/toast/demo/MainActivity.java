package com.zpj.toast.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.zpj.toast.ZToast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZToast.normal("消息");
            }
        });

        findViewById(R.id.btn_success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZToast.success("成功");
            }
        });

        findViewById(R.id.btn_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZToast.error("出错了");
            }
        });

        findViewById(R.id.btn_warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZToast.warning("警告11111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
            }
        });

        findViewById(R.id.btn_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ZToast.with("1111111111111111111111")
                                .success()
                                .setGravity(Gravity.TOP)
                                .setDuration(Toast.LENGTH_LONG)
                                .show();
                    }
                }).start();
            }
        });


    }

}
