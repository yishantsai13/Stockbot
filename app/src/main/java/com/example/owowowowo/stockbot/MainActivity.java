package com.example.owowowowo.stockbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.postDelayed(runnable,2000);
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(MainActivity.this,Main2Activity.class));
            finish();
        }
    };

}
