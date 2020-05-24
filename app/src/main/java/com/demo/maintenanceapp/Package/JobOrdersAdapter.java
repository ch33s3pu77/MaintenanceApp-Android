package com.demo.maintenanceapp.Package;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.maintenanceapp.Model.JobOrders;
import com.demo.maintenanceapp.R;

import java.util.List;

public class JobOrdersAdapter extends ArrayAdapter<JobOrders> {
    private List<JobOrders> jobOrdersList;
    private Context context;

    public JobOrdersAdapter(List<JobOrders> jobOrdersList, Context context){
        super(context, R.layout.list_my_jobs, jobOrdersList);
        this.jobOrdersList = jobOrdersList;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.list_my_jobs, null, true);

        TextView jobId = (TextView) view.findViewById(R.id.txt_ID);
        TextView type = (TextView) view.findViewById(R.id.txt_Type);
        TextView priority = (TextView) view.findViewById(R.id.txt_Priority);
        TextView status = (TextView) view.findViewById(R.id.txt_Status);
        TextView location = (TextView) view.findViewById(R.id.txt_Location);

        JobOrders jobOrder = jobOrdersList.get(position);
        jobId.setText(jobOrder.getmJobId());
        type.setText(jobOrder.getmType());
        priority.setText(jobOrder.getmPriority());
        status.setText(jobOrder.getmStatus());
        location.setText(jobOrder.getmLocation());

        return view;
    }


}
