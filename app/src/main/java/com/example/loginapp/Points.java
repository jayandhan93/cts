package com.example.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Points extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
    }


    private void Logout(){

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Points.this,MainActivity.class));
        Toast.makeText(Points.this, "Loging Out!!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.welcome:{
                startActivity(new Intent(Points.this,Homepage.class));
            }

            case R.id.logoutMenu:{
                Logout();

            }
            case R.id.settings:{
                Toast.makeText(Points.this, "Under construction", Toast.LENGTH_SHORT).show();
            }
            case R.id.help:{
                Toast.makeText(Points.this, "Under construction", Toast.LENGTH_SHORT).show();
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
