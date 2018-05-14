package com.example.owowowowo.stockbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class selectStock extends AppCompatActivity {
    public ArrayList<String> highname=new ArrayList<>();
    public ArrayList<String> highno=new ArrayList<>();
    public ArrayList<String> highprice=new ArrayList<>();
    public ArrayList<String>midname=new ArrayList<>();
    public ArrayList<String>midno=new ArrayList<>();
    public ArrayList<String>midprice=new ArrayList<>();
    public ArrayList<String>lowname=new ArrayList<>();
    public ArrayList<String>lowno=new ArrayList<>();
    public ArrayList<String>lowprice=new ArrayList<>();
    public ArrayList<String>stockid=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stock);
        Button high=(Button)findViewById(R.id.high);
        Button mid=(Button)findViewById(R.id.mid);
        Button low=(Button)findViewById(R.id.low);
        getdata();

        high.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                // TODO Auto-generated method stub

                getid(1);

            }

        });
        mid.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                // TODO Auto-generated method stub

                getid(2);

            }

        });
        low.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                // TODO Auto-generated method stub

                getid(3);

            }

        });
    }

    private void getdata() {
        final TextView tv=(TextView)findViewById (R.id.tv);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("分析結果");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot da : dataSnapshot.getChildren()) {
                    tv.setText (da.getKey ().toString ());
                    if (da.getKey ().toString ().equals("一般")) {

                        for (DataSnapshot dada : da.getChildren()) {
                            for (DataSnapshot dadada : dada.getChildren()) {
                                if (dadada.getKey().equals("name")) {
                                    String st = dadada.getValue().toString();
                                    midname.add(st);
                                } else if (dadada.getKey().equals("no")) {
                                    String st = dadada.getValue().toString();
                                    midno.add(st);
                                }
                            }
                        }

                    } else if (da.getKey ().toString ().equals("低報酬")) {
                        for (DataSnapshot dada : da.getChildren()) {
                            for (DataSnapshot dadada : dada.getChildren()) {
                                if (dadada.getKey().equals("name")) {
                                    String st = dadada.getValue().toString();
                                    lowname.add(st);
                                } else if (dadada.getKey().equals("no")) {
                                    String st = dadada.getValue().toString();
                                    lowno.add(st);
                                }
                            }
                        }

                    }
                    else if(da.getKey ().toString ().equals("高報酬")) {
                        for (DataSnapshot dada : da.getChildren()) {
                            for (DataSnapshot dadada : dada.getChildren()) {
                                if (dadada.getKey().equals("name")) {
                                    String st = dadada.getValue().toString();
                                    highname.add(st);
                                } else if (dadada.getKey().equals("no")) {
                                    String st = dadada.getValue().toString();
                                    highno.add(st);
                                }
                            }
                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }

    private void getid(int haha){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;

                for(DataSnapshot da:dataSnapshot.getChildren()){
                    String st=da.getKey().toString();
                    if(st.equals("z")||st.equals("today")||st.equals("news")||st.equals("分析結果"))
                        continue;
                    stockid.add(st);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        if(haha==1){
            setContentView(R.layout.high);
            ListView list1=(ListView)findViewById (R.id.list1);
            TextView tv=(TextView)findViewById (R.id.tv);
            String s = Integer.toString(highname.size ());
            tv.setText (s);
            ListAdapter mAdapter = new ArrayAdapter<String> (this,
                    android.R.layout.simple_list_item_1,
                    highname);
            list1.setAdapter(mAdapter);
            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });
        }
        else if(haha==2){
            setContentView(R.layout.normal);
            ListView list3=(ListView)findViewById (R.id.list3);
            ListAdapter mAdapter = new ArrayAdapter<String> (this,
                    android.R.layout.simple_list_item_1,
                    midname);
            list3.setAdapter(mAdapter);
            list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });
        }
        else if(haha==3) {
            setContentView(R.layout.low);
            ListView list2=(ListView)findViewById (R.id.list2);
            ListAdapter mAdapter = new ArrayAdapter<String> (this,
                    android.R.layout.simple_list_item_1,
                    lowname);
            list2.setAdapter(mAdapter);
            list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });
        }
    }

}
