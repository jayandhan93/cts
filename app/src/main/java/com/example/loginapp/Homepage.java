package com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Homepage extends AppCompatActivity {
    private Button signout,profile,ufiles,mtask,mperformance,points;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        signout = (Button)findViewById(R.id.btnSignOut);
        profile = (Button)findViewById(R.id.btnUProfile);
        mtask = (Button)findViewById(R.id.btnMonthlyTasks);
        mperformance = (Button)findViewById(R.id.btnMPerformance);
        points = (Button)findViewById(R.id.btnPoints);


        firebaseAuth=FirebaseAuth.getInstance();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,UserProfile.class));
            }
        });
        mtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,MonthlyTask.class));
            }
        });
        mperformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,MonthPerf.class));
            }
        });
        points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Homepage.this,Points.class));
            }
        });





    }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Homepage.this,MainActivity.class));
        Toast.makeText(Homepage.this, "Loging Out!!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
            }
            case R.id.welcome:{
                Toast.makeText(Homepage.this,"Welcome to students club!!",Toast.LENGTH_SHORT).show();
            }
            case R.id.settings:{
                //settings page
            }
            case R.id.help:{
                //help page
            }
        }
        return true;
    }
}
