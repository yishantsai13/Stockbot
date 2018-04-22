package com.example.owowowowo.stockbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;

public class mainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        // stock button Activity
        ImageView stock_button = (ImageView)findViewById(R.id.btn_stock);
        stock_button.setOnClickListener(new ViewClickListener());
        @Override
        class ViewClickListener implements OnClickListener {
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(mainpage.this, stockactivity.class);
                startActivity(i);
            }
        }
    };
}
