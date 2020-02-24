package com.example.hosteltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Apply extends AppCompatActivity {

    private Button btnSubmit;
    EditText name,phone,roll,tmark,gmark;
    Spinner citySpinner;
    Student student;
    DatabaseReference reff;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phoneNumber);
        roll = (EditText) findViewById(R.id.rollNumber);
        tmark = (EditText) findViewById(R.id.twelvethMark);
        gmark = (EditText) findViewById(R.id.gujcetMark);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);
        student = new Student();
        reff = FirebaseDatabase.getInstance().getReference().child("Student");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    id = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setName(name.getText().toString().trim());
                student.setPhone(phone.getText().toString().trim());
                student.setRoll(roll.getText().toString().trim());
                student.setGmark(gmark.getText().toString().trim());
                student.setTmark(tmark.getText().toString().trim());
                student.setCity(citySpinner.getSelectedItem().toString().trim());
                student.setStatus(false);
                float gm = Float.parseFloat(gmark.getText().toString());
                int lp = (int) gm;
                float tm = gm - (float) lp ;
                int lq = (int) tm*100;
                reff.child(lp+""+lq+""+(id+1)).setValue(student);
                Toast.makeText(Apply.this,"SUCCESS",Toast.LENGTH_LONG).show();
            }
        });
    }
}
