package com.example.loginapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MonthlyTask extends AppCompatActivity {

    private ListView listView;
    private ImageButton imageButton;
    private FirebaseAuth firebaseAuth;
    private CustomAdapter arrayAdapter;
    private String task;
    private Dbhelper dbhelper;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add: {

                final EditText editText = new EditText(MonthlyTask.this);
                AlertDialog alertDialog = new AlertDialog.Builder(MonthlyTask.this)
                        .setTitle("Monthly Task")
                        .setMessage("What's Your task ?")
                        .setView(editText)
                        .setPositiveButton("add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                task = editText.getText().toString();
                                dbhelper.insertValues(task);
                                sendTaskData(task);
                                loadTask();


                            }
                        })
                        .setNegativeButton("cancel",null)
                        .create();
                alertDialog.show();


                return true;
            }
            case R.id.home: {
                startActivity(new Intent(MonthlyTask.this, Homepage.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addlist, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_task);

        firebaseAuth = FirebaseAuth.getInstance();

        imageButton = (ImageButton)findViewById(R.id.ibAdd);
        listView = (ListView) findViewById(R.id.MTlist);
        dbhelper = new Dbhelper(this);
        loadTask();


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(MonthlyTask.this);
                AlertDialog alertDialog = new AlertDialog.Builder(MonthlyTask.this)
                        .setTitle("Monthly Task")
                        .setMessage("What's Your task ?")
                        .setView(editText)
                        .setPositiveButton("add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                task = editText.getText().toString();
                                dbhelper.insertValues(task);
                                sendTaskData(task);
                                loadTask();

                            }
                        })
                        .setNegativeButton("cancel",null)
                        .create();
                alertDialog.show();

            }
        });



    }

    private void sendTaskData(String task){
        this.task=task;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        MonthlyTaskList monthlyTaskList = new MonthlyTaskList(task);
        myRef.setValue(monthlyTaskList);

    }
    private void loadTask() {
        listView = (ListView) findViewById(R.id.MTlist);
        ArrayList<String> taskList = dbhelper.getTaskList();
        arrayAdapter = new CustomAdapter(this, taskList);
        listView.setAdapter(arrayAdapter);
    }

    public void deleteTask(View view) {
        try {
            int i = listView.getPositionForView(view);
            String task = arrayAdapter.getItem(i);
            dbhelper.deleteValues(task);
            loadTask();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}




//code for offline usage
/*

package com.example.loginapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MonthlyTask extends AppCompatActivity {

    Dbhelper dbhelper;
    ListView listView;
    CustomAdapter arrayAdapter;
    ImageButton imageButton;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add: {
                final EditText editText = new EditText(MonthlyTask.this);
                AlertDialog alertDialog = new AlertDialog.Builder(MonthlyTask.this)
                        .setTitle("Monthly Task")
                        .setMessage("What's Your task ?")
                        .setView(editText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String task = editText.getText().toString();
                                dbhelper.insertValues(task);
                                loadTask();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
                return true;
            }
            case R.id.home:{
                startActivity(new Intent(MonthlyTask.this,Homepage.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addlist,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_task);
        imageButton = (ImageButton)findViewById(R.id.ibAdd);
        dbhelper = new Dbhelper(this);
        loadTask();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText editText = new EditText(MonthlyTask.this);
                AlertDialog alertDialog = new AlertDialog.Builder(MonthlyTask.this)
                        .setTitle("Monthly Task")
                        .setMessage("What's Your task ?")
                        .setView(editText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String task = editText.getText().toString();
                                dbhelper.insertValues(task);
                                loadTask();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
                //return true;

            }
        });
    }
    private void loadTask() {
        listView = (ListView) findViewById(R.id.MTlist);
        ArrayList<String> taskList = dbhelper.getTaskList();
        arrayAdapter = new CustomAdapter(this, taskList);
        listView.setAdapter(arrayAdapter);
    }

    public void deleteTask(View view) {
        try {
            int i = listView.getPositionForView(view);
            String task = arrayAdapter.getItem(i);
            dbhelper.deleteValues(task);
            loadTask();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}


 */


