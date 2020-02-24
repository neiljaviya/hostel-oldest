package com.example.hosteltest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class Allocation extends AppCompatActivity {

    DatabaseReference reff;
    ArrayList<String> arrayList = new ArrayList<String>();
    MyCustomAdapter arrayAdapter = new MyCustomAdapter(arrayList, this);
    ListView lw;
    long id;
    int j=0;
    Student[] student = new Student[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation);

        reff = FirebaseDatabase.getInstance().getReference("Student");
        lw = (ListView) findViewById(R.id.listview);
        lw.setAdapter(arrayAdapter);


        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists())
                {
                    id = dataSnapshot.getChildrenCount();
                }
                for(int i=1;i<=id;i=i+9) {
                    reff = FirebaseDatabase.getInstance().getReference().child("Student");
                    Student value = dataSnapshot.getValue(Student.class);
                    String name = value.getName();
                    String city = value.getCity();
                    String phone = value.getPhone();
                    String roll = value.getRoll();
                    String gmark = value.getGmark();
                    String tmark = value.getTmark();
                    Boolean stat = value.isStatus();
                    student[j] = new Student();
                    student[j] = value;
                    if(stat == false)
                    {
                        arrayList.add(student[j].getName()+"\n"+student[j].getPhone()+"\n"+student[j].getGmark());
                        arrayAdapter.notifyDataSetChanged();
                    }
                    j++;
                }


            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
