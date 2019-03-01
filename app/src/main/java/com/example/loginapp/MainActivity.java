package com.example.loginapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button login;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.etUsername);
        password = (EditText)findViewById(R.id.etPassword);
        login = (Button)findViewById(R.id.rfbtnLogin);
        signup = (Button)findViewById(R.id.btSignUp);
        forgot = (TextView)findViewById(R.id.tvForgotpass);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailid = name.getText().toString().trim();

                if(mailid.equals("")){
                    Toast.makeText(MainActivity.this,"Enter a valid mail id.",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(mailid).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Password reset mail sent successful.",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this,"Password reset mail sent unsuccessful.",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });


        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null ){
            finish();
            startActivity(new Intent(MainActivity.this,Homepage.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(),password.getText().toString());
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regintent =new Intent(MainActivity.this,RegForm.class);
                startActivity(regintent);

            }
        });
    }
    private void validate(String userName,String userPassword) {
        progressDialog.setMessage("Loging In with your mailID and password...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Homepage.class));

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
