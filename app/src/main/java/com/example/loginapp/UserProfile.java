package com.example.loginapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserProfile extends AppCompatActivity {

    private TextView name,mail,phno,dpt,rno,ename,email,ephno,edpt,erno;
    private Button update;
    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = (TextView)findViewById(R.id.tvName);
        mail = (TextView)findViewById(R.id.tvEmail);
        phno = (TextView)findViewById(R.id.tvPno);
        dpt = (TextView)findViewById(R.id.tvDpt);
        rno = (TextView)findViewById(R.id.tvRno);
        ename = (TextView)findViewById(R.id.tvename);
        email = (TextView)findViewById(R.id.tveemail);
        ephno = (TextView)findViewById(R.id.tvepno);
        edpt = (TextView)findViewById(R.id.tvedpt);
        erno = (TextView)findViewById(R.id.tverno);



        ename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(UserProfile.this);
                AlertDialog alertDialog = new AlertDialog.Builder(UserProfile.this)
                        .setTitle("Enter The Name")
                        .setView(editText)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String temp = editText.getText().toString();
                                name.setText("" +temp);
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                alertDialog.show();

             }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(UserProfile.this);
                AlertDialog alertDialog = new AlertDialog.Builder(UserProfile.this)
                        .setTitle("Enter The Email")
                        .setView(editText)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String temp = editText.getText().toString();
                                mail.setText("" +temp);
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                alertDialog.show();

            }
        });
        ephno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(UserProfile.this);
                AlertDialog alertDialog = new AlertDialog.Builder(UserProfile.this)
                        .setTitle("Enter The Phone Number")
                        .setView(editText)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String temp = editText.getText().toString();
                                phno.setText("" +temp);
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                alertDialog.show();

            }
        });
        edpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(UserProfile.this);
                AlertDialog alertDialog = new AlertDialog.Builder(UserProfile.this)
                        .setTitle("Enter The Department")
                        .setView(editText)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String temp = editText.getText().toString();
                                dpt.setText("" +temp);
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                alertDialog.show();

            }
        });
        erno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(UserProfile.this);
                AlertDialog alertDialog = new AlertDialog.Builder(UserProfile.this)
                        .setTitle("Enter The Register No")
                        .setView(editText)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String temp = editText.getText().toString();
                                rno.setText("" +temp);
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                alertDialog.show();

            }
        });
    }
    private void Logout(){

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(UserProfile.this,MainActivity.class));
        Toast.makeText(UserProfile.this, "Loging Out!!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.welcome:{
                startActivity(new Intent(UserProfile.this,Homepage.class));
            }

            case R.id.logoutMenu:{
                Logout();

            }
            case R.id.settings:{
                Toast.makeText(UserProfile.this, "Under construction", Toast.LENGTH_SHORT).show();
            }
            case R.id.help:{
                Toast.makeText(UserProfile.this, "Under construction", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
