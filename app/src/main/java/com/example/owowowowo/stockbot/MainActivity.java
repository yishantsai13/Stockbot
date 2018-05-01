package com.example.owowowowo.stockbot;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String>ar=new ArrayList<>();
    public ArrayList<String>second=new ArrayList<>();
    public ArrayList<String>thirdvalue=new ArrayList<>();
    public ArrayList<String>thirdkey=new ArrayList<>();





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton back=(ImageButton) findViewById(R.id.back);
        ImageButton back1=(ImageButton)findViewById(R.id.back1);
        ImageButton back2=(ImageButton)findViewById(R.id.back2);

        /*back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.second);
            }
        });*/


        jumpToLayout01();

    }

//第一頁面的
    private void initViews() {
        ListView mList = (ListView) findViewById(R.id.lv);
        final TextView tv=(TextView)findViewById(R.id.tv);
        ImageButton back1=(ImageButton)findViewById(R.id.back1);

        ListAdapter mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                ar);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                jumpToLayout02(ar.get(i));
            }
        });
    }

//第二頁面
    private void initViews2(final String ch) {
        ListView mList = (ListView) findViewById(R.id.lv1);

        ListAdapter mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                second);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                jumpToLayout03(ch,second.get(i));
            }
        });
    }

    //第三頁面
    private void initViews3() {
        TextView tv2=(TextView)findViewById(R.id.tv2);
        tv2.setTextSize(25);
        for(int i=0;i<thirdkey.size();i++){
            tv2.append(thirdkey.get(i).toString());
            tv2.append(":");
            tv2.append(thirdvalue.get(i).toString());
            tv2.append("\n");
        }
//        a=thirdkey.get(0);
//        b=thirdkey.get(1);
//        c=thirdkey.get(2);
//        d=thirdkey.get(3);
//        e=thirdkey.get(4);
//        f=thirdkey.get(5);
//        g=thirdkey.get(6);
//        h=thirdkey.get(7);
//        i=thirdkey.get(8);
//        j=thirdkey.get(9);
//        k=thirdkey.get(10);
//        l=thirdkey.get(11);
//        m=thirdkey.get(12);
//        n=thirdkey.get(13);
//        o=thirdkey.get(14);
//        p=thirdkey.get(15);
////value
//        a1=thirdvalue.get(0);
//        b1=thirdvalue.get(1);
//        c1=thirdvalue.get(2);
//        d1=thirdvalue.get(3);
//        e1=thirdvalue.get(4);
//        f1=thirdvalue.get(5);
//        g1=thirdvalue.get(6);
//        h1=thirdvalue.get(7);
//        i1=thirdvalue.get(8);
//        j1=thirdvalue.get(9);
//        k1=thirdvalue.get(10);
//        l1=thirdvalue.get(11);
//        m1=thirdvalue.get(12);
//        n1=thirdvalue.get(13);
//        o1=thirdvalue.get(14);
//        p1=thirdvalue.get(15);

    }


    public void jumpToLayout01(){
        ar.clear();
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;

                for(DataSnapshot da:dataSnapshot.getChildren()){
                    String st=da.getKey().toString();
                    if(st.equals("z")||st.equals("today"))
                        continue;
                    ar.add(st);
                }

                initViews();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

//1跳2
    public void jumpToLayout02(final String ch){
        setContentView(R.layout.second);
        second.clear();
        ImageButton back1=(ImageButton)findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backto1();
            }
        });

        DatabaseReference mDatabase;
// 指向目前的database 位置
        //ondatachange偵測到資料變動自動更新

        mDatabase = FirebaseDatabase.getInstance().getReference().child(ch);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                second.clear();
                for(DataSnapshot da:dataSnapshot.getChildren()){
                    String st=da.getKey().toString();

                    second.add(st);
                }
                initViews2(ch);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//2跳3
    public void jumpToLayout03(final String ch, String ch1){
        setContentView(R.layout.third);
        ImageButton back2=(ImageButton)findViewById(R.id.back2);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpToLayout02(ch);
            }
        });

        DatabaseReference mDatabase;


// 指向目前的database 位置
        //ondatachange偵測到資料變動自動更新

        mDatabase = FirebaseDatabase.getInstance().getReference().child(ch).child(ch1);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                thirdkey.clear();
                thirdvalue.clear();
                for(DataSnapshot da:dataSnapshot.getChildren()){
                    for(DataSnapshot dada:da.getChildren()) {
                        String st = dada.getKey().toString();
                        thirdkey.add(st);
                        st=dada.getValue().toString();
                        thirdvalue.add(st);



                    }
                }
                initViews3();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void backto1(){
        setContentView(R.layout.activity_main);
        ar.clear();
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;

                for(DataSnapshot da:dataSnapshot.getChildren()){
                    String st=da.getKey().toString();

                    ar.add(st);
                }
                initViews();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}



