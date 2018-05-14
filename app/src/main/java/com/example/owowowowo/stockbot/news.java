package com.example.owowowowo.stockbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ListAdapter;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class news extends AppCompatActivity {
    public ArrayList<String>news=new ArrayList<>();
    public ArrayList<String>newsurl=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getnews();

        ImageView btn_backhome=(ImageView)findViewById(R.id.back_home);
        btn_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setClass(news.this,homepage.class);
                startActivity(i);
            }
        });
    }

    private void getnews(){

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("news");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;

                for(DataSnapshot da:dataSnapshot.getChildren()){
                    for(DataSnapshot dada:da.getChildren()){
                        if(dada.getKey().equals("標題")){
                            String st=dada.getValue().toString();
                            news.add(st);
                        }
                        else{
                            String st=dada.getValue().toString();
                            newsurl.add(st);
                        }
                    }
                }
                initViews();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }


    private void initViews(){
        ListView newslv=(ListView)findViewById(R.id.newslv);

        ListAdapter mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                news);
        newslv.setAdapter(mAdapter);
        newslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent();
                in.setClass(news.this,web.class);
                in.putExtra("url",newsurl.get(i));
                startActivity(in);
            }
        });
    }

    private void jumptonews(){
        setContentView(R.layout.activity_news);
        getnews();
        ImageButton button3=(ImageButton)findViewById(R.id.back_home);

    }


    private void jumptoweb(String url){
        setContentView(R.layout.webnews);
    }
}
