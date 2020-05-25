package com.demo.maintenanceapp.output;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.demo.maintenanceapp.Dashboard;
import com.demo.maintenanceapp.model.JobOrders;
import com.demo.maintenanceapp.adapterPackage.JobOrdersAdapter;
import com.demo.maintenanceapp.R;
import com.demo.maintenanceapp.input.NewJob;
import com.demo.maintenanceapp.storage.SharedPrefManager;
import com.demo.maintenanceapp.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Completed extends AppCompatActivity {
    private String JOB_ID, TYPE, PRIORITY, LOCATION, USERNAME, appURL;
    Activity mContext = this;
    TextView mJobId, mType, mPriority, mLocation;
    Button mClose, mNewJob;

    ListView listView;
    List<JobOrders> jobOrdersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        //mJobId = findViewById(R.id.txt_ID);
        //mType = findViewById(R.id.txt_Type);
        //mPriority = findViewById(R.id.txt_Priority);
        //mLocation = findViewById(R.id.txt_Location);

        listView = (ListView) findViewById(R.id.lv_Completed);
        jobOrdersList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent intent = new Intent(getApplicationContext(), JobOrder.class);
                startActivity(intent);
            }
        });

        mClose = findViewById(R.id.btn_close);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });

        mNewJob = findViewById(R.id.btn_newJob);
        mNewJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewJob.class);
                startActivity(intent);
            }
        });

        SharedPrefManager sharedPrefManager = new SharedPrefManager();
        USERNAME = sharedPrefManager.getUsername(mContext);
        appURL = "192.168.247.100/api/completed.php?User_Name=" + USERNAME;
        getJobs();
    }

    public void getJobs(){
        if(USERNAME.isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            alert.setMessage("Username cannot be empty");
            alert.setCancelable(false);
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, appURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("completed");
                        for (int i = 0; i < array.length(); i++){
                            JSONObject jobObj = new JSONObject(response);
                            JobOrders jobOrder = new JobOrders(jobObj.getString("Job_ID"),
                                    jobObj.getString("Type"), jobObj.getString("Priority"),
                                    jobObj.getString("Location"));
                            jobOrdersList.add(jobOrder);
                        }
                        JobOrdersAdapter adapter = new JobOrdersAdapter(jobOrdersList,getApplicationContext());
                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    AlertDialog.Builder alert;
                    NetworkResponse response = error.networkResponse;
                    if(response != null && response.data != null){
                        switch(response.statusCode){
                            case 400:
                                alert = new AlertDialog.Builder(mContext);
                                alert.setTitle("Error");
                                alert.setMessage(response.data.toString());
                                alert.setCancelable(false);
                                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                alert.show();
                                break;
                            case 404:
                                alert = new AlertDialog.Builder(mContext);
                                alert.setTitle("Error");
                                alert.setMessage(response.data.toString());
                                alert.setCancelable(false);
                                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                alert.show();
                                break;
                            case 403:
                                alert = new AlertDialog.Builder(mContext);
                                alert.setTitle("Error");
                                alert.setMessage(response.data.toString());
                                alert.setCancelable(false);
                                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                alert.show();
                                break;
                        }
                    } else {
                        alert = new AlertDialog.Builder(mContext);
                        alert.setTitle("Error");
                        alert.setMessage("There was an error. \n Please try again.");
                        alert.setCancelable(false);
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alert.show();
                    }
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("Accept", "Application/json; charset=UTF-8");
                    return params;
                }
            };
            VolleySingleton.getInstance().addRequestQueue(stringRequest);
        }
    }
}
