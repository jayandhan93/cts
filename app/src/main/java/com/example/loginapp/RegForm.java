package com.example.loginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginapp.UserDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegForm extends AppCompatActivity {
    private EditText rfname;
    private EditText rfpassword;
    private EditText rfmail;
    private Button rflogin;
    private Button rfsignup;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String usermail,username,userpass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_form);
        rfname = (EditText)findViewById(R.id.rftvName);
        rfpassword = (EditText)findViewById(R.id.rftvPassword);
        rfmail = (EditText)findViewById(R.id.rftvEmail);
        rflogin = (Button)findViewById(R.id.rfbtnLogin);
        rfsignup = (Button)findViewById(R.id.rfbtnReg);

        firebaseAuth = FirebaseAuth.getInstance();


        rflogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rfintent = new Intent(RegForm.this,MainActivity.class);
                startActivity(rfintent);
            }
        });
        rfsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usermail = rfmail.getText().toString().trim();
                username = rfname.getText().toString().trim();
                userpass = rfpassword.getText().toString().trim();
                firebaseAuth.createUserWithEmailAndPassword(usermail,userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            sendUserData();
                            Toast.makeText(RegForm.this, "registration successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegForm.this,MainActivity.class));
                        }else {
                            Toast.makeText(RegForm.this, "registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserDatabase userDatabase = new UserDatabase(username,usermail,userpass);
        myRef.setValue(userDatabase);


    }
}
