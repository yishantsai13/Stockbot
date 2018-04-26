package com.example.owowowowo.stockbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;


public class homepage extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        ImageView stockButton = (ImageView)findViewById(R.id.btn_stock);
         //stock button Activity
        stockButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(homepage.this, MainActivity.class);
                startActivity(i);
            }
        });

        ImageView btn_news=(ImageView)findViewById(R.id.btn_news);
        btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(homepage.this,news.class);
                startActivity(i);
            }
        });
    }
}
