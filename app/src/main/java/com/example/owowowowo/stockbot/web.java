package com.example.owowowowo.stockbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class web extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        Intent it=getIntent();
        String url=it.getStringExtra("url");
        WebView wv=(WebView)findViewById(R.id.wv);
        wv.loadUrl(url);
    }
}
