package com.demo.maintenanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.maintenanceapp.output.Completed;
import com.demo.maintenanceapp.output.InProgress;
import com.demo.maintenanceapp.output.MyJobs;
import com.demo.maintenanceapp.output.NewJobs;
import com.demo.maintenanceapp.output.NotStarted;
import com.demo.maintenanceapp.output.OnHold;

public class Dashboard extends AppCompatActivity {
    Button btnMyJobs, btnCompleted, btnOnHold, btnInProgress, btnNotStarted, btnNewJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnMyJobs = findViewById(R.id.btn_MyJobs);
        btnMyJobs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MyJobs.class);
                startActivity(intent);
            }
        });

        btnCompleted = findViewById(R.id.btn_Complete);
        btnCompleted.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), Completed.class);
                startActivity(intent);
            }
        });

        btnOnHold = findViewById(R.id.btn_OnHold);
        btnOnHold.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), OnHold.class);
                startActivity(intent);
            }
        });

        btnInProgress = findViewById(R.id.btn_InProgress);
        btnInProgress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), InProgress.class);
                startActivity(intent);
            }
        });

        btnNotStarted = findViewById(R.id.btn_NotStarted);
        btnNotStarted.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), NotStarted.class);
                startActivity(intent);
            }
        });

        btnNewJobs = findViewById(R.id.btn_NewJobs);
        btnNewJobs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), NewJobs.class);
                startActivity(intent);
            }
        });
    }
}
