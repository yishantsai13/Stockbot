package com.example.owowowowo.stockbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class select_stocks extends AppCompatActivity {
    ArrayList<String>arr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_select_stocks);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("分析結果").child ("一般");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot da:dataSnapshot.getChildren()){
                    for(DataSnapshot dada:da.getChildren()){
                        for(DataSnapshot dadada:dada.getChildren ()){
                            String st=dadada.getValue().toString();
                            arr.add(st);
                        }

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
