package com.example.owowowowo.stockbot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class today extends AppCompatActivity {
    ArrayList<String>ar=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("today");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;

                for(DataSnapshot da:dataSnapshot.getChildren()){
                    for(DataSnapshot dada:da.getChildren()){
                        String st=dada.getKey().toString()+":\n"+dada.getValue().toString();
                        ar.add(st);
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
        TextView tv1=(TextView)findViewById(R.id.tv1);
        TextView tv2=(TextView)findViewById(R.id.tv2);
        TextView tv3=(TextView)findViewById(R.id.tv3);
        TextView tv4=(TextView)findViewById(R.id.tv4);
        TextView tv5=(TextView)findViewById(R.id.tv5);
        TextView tv6=(TextView)findViewById(R.id.tv6);
        TextView tv7=(TextView)findViewById(R.id.tv7);
        TextView tv8=(TextView)findViewById(R.id.tv8);
        ImageButton back1=(ImageButton)findViewById(R.id.back1);

        tv1.setText(ar.get(5).toString());//最近成交價
        tv2.setText(ar.get(8).toString());//累積成交量
        tv3.setText(ar.get(4).toString());//昨收
        tv4.setText(ar.get(7).toString());//漲跌差價
        tv5.setText(ar.get(9).toString());//百分比
        tv6.setText(ar.get(11).toString());//開盤價
        tv7.setText(ar.get(2).toString());//今日最低
        tv8.setText(ar.get(3).toString());//今日最高

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(today.this, homepage.class);
                startActivity(intent);
                today.this.finish();
            }
        });


    }
}
