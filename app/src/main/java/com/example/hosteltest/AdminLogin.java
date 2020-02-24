package com.example.hosteltest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogin extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignInA;
    private EditText editTextEmailA;
    private EditText editTextPasswordA;
    private String emaild;
    private String passd;

    private DatabaseReference reff;

    //progress dialog
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        //getting firebase auth object
        reff = FirebaseDatabase.getInstance().getReference();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        //initializing views
        editTextEmailA = (EditText) findViewById(R.id.editTextAdminEmail);
        editTextPasswordA = (EditText) findViewById(R.id.editTextAdminPassword);
        buttonSignInA = (Button) findViewById(R.id.adminButtonSignin);

        progressDialog = new ProgressDialog(this);
        buttonSignInA.setOnClickListener(this);


    }
    private void adminLogin(){
        String email = editTextEmailA.getText().toString().trim();
        String password  = editTextPasswordA.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //logging in the user
        emaild = "neiljaviya4@gmail.com";
        passd = "a";

        if(emaild.equals(email) && passd.equals(password)) {
            progressDialog.dismiss();
            startActivity(new Intent(getApplicationContext(),Allocation.class));
        }
        else
        {
            progressDialog.dismiss();
            Toast.makeText(this,"Enter valid credentials",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignInA){
            adminLogin();
        }
    }

}
