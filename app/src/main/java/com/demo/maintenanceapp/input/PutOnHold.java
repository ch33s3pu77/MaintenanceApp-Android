package com.demo.maintenanceapp.input;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.maintenanceapp.Dashboard;
import com.demo.maintenanceapp.R;

public class PutOnHold extends AppCompatActivity {
    Button mCancel, mSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_on_hold);
        mCancel = findViewById(R.id.btn_cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });

        mSave = findViewById(R.id.btn_save);

    }
}
