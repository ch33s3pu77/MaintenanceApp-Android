package com.demo.maintenanceapp.output;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.demo.maintenanceapp.Dashboard;
import com.demo.maintenanceapp.R;
import com.demo.maintenanceapp.input.NewJob;
import com.demo.maintenanceapp.input.SignOff;
import com.demo.maintenanceapp.storage.SharedPrefManager;
import com.demo.maintenanceapp.volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JobOrder extends AppCompatActivity {
    private String JOB_ID, ASSET_ID, DESCRIPTION, DUE_BY, DATE_STARTED, ON_HOLD_DATE,
            ON_HOLD_REASON, DATE_COMPLETED, FORENAME, PRIORITY, STATUS, ASSET_DESCRIPTION,
            LOCATION, USERNAME, appURL;
    Activity mContext = this;
    TextView mJobId, mAssetId, mDescription, mDueBy, mDateStarted, mOnHoldDate,
            mOnHoldReason, mDateCompleted, mForename, mPriority, mStatus,
            mAssetDescription, mLocation;
    Button mClose, mSignOff, mOnHold, mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_order);
        mJobId = findViewById(R.id.txt_outputJob);
        mAssetId = findViewById(R.id.txt_ouputAssetID);
        mDescription = findViewById(R.id.txt_outputTaskDesription);
        mDueBy = findViewById(R.id.txt_outputDueBy);
        mDateStarted = findViewById(R.id.txt_outputDueBy);
        mOnHoldDate = findViewById(R.id.txt_outputHoldReason);
        mOnHoldReason = findViewById(R.id.txt_outputHoldReason);
        mDateCompleted = findViewById(R.id.txt_outputSignedOff);
        mForename = findViewById(R.id.txt_outputAssignedTo);
        mAssetDescription = findViewById(R.id.txt_outputAsset);
        mPriority = findViewById(R.id.txt_outputPriority);
        mStatus = findViewById(R.id.txt_outputStatus);
        mLocation = findViewById(R.id.txt_outputLocation);
        mClose = findViewById(R.id.btn_close);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });
        mSignOff = findViewById(R.id.btn_signOff);
        mSignOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignOff.class);
                startActivity(intent);
            }
        });
        mOnHold = findViewById(R.id.btn_OnHold);
        mStart = findViewById(R.id.btn_start);
        SharedPrefManager sharedPrefManager = new SharedPrefManager();
        USERNAME = sharedPrefManager.getUsername(mContext);
        appURL = "192.168.247.100/api/jobOrder.php?User_Name=" + USERNAME;
        getJobOrder();
    }

    public void getJobOrder(){
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
                        JOB_ID = jsonObject.getString("Job_ID");
                        ASSET_ID = jsonObject.getString("Asset_ID");
                        DESCRIPTION = jsonObject.getString("Description");
                        DUE_BY = jsonObject.getString("Due_By");
                        DATE_STARTED = jsonObject.getString("Date_Started");
                        ON_HOLD_DATE = jsonObject.getString("On_Hold_Date");
                        ON_HOLD_REASON = jsonObject.getString("On_Hold_Reason");
                        DATE_COMPLETED = jsonObject.getString("Date_Completed");
                        FORENAME = jsonObject.getString("Forename");
                        PRIORITY = jsonObject.getString("Priority");
                        STATUS = jsonObject.getString("Status");
                        ASSET_DESCRIPTION = jsonObject.getString("Asset_Description");
                        LOCATION = jsonObject.getString("Location");

                        mJobId.setText(JOB_ID);
                        mAssetId.setText(ASSET_ID);
                        mDescription.setText(DESCRIPTION);
                        mDueBy.setText(DUE_BY);
                        mDateStarted.setText(DATE_STARTED);
                        mOnHoldDate.setText(ON_HOLD_DATE);
                        mOnHoldReason.setText(ON_HOLD_REASON);
                        mDateCompleted.setText(DATE_COMPLETED);
                        mForename.setText(FORENAME);
                        mPriority.setText(PRIORITY);
                        mStatus.setText(STATUS);
                        mAssetDescription.setText(ASSET_DESCRIPTION);
                        mLocation.setText(LOCATION);

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

