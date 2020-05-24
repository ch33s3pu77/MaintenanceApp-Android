package com.demo.maintenanceapp.Package;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.demo.maintenanceapp.Model.Asset;
import com.demo.maintenanceapp.Model.JobOrders;
import com.demo.maintenanceapp.R;

import java.util.List;

public class AssetAdapter extends ArrayAdapter<Asset> {
    private List<Asset> assetList;
    private Context context;

    public AssetAdapter(List<Asset> assetList, Context context){
        super(context, R.layout.asset_spinner_layout, assetList);
        this.assetList = assetList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.asset_spinner_layout, null, true);

        TextView assetName = (TextView) view.findViewById(R.id.txt_Item);

        Asset asset = assetList.get(position);
        assetName.setText(asset.getName());

        return view;
    }
}
