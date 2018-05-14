package com.example.owowowowo.stockbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;


public class homepage extends navigation {



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

        ImageView btn_today=(ImageView)findViewById(R.id.btn_today);
        btn_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(homepage.this,today.class);
                startActivity(i);
            }
        });

        ImageView btn_select=(ImageView)findViewById(R.id.btn_select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(homepage.this,selectStock.class);
                startActivity(i);
            }
        });
    }
}
