package com.demo.maintenanceapp.Model;

public class JobOrders {
    String mJobId, mType, mPriority, mStatus, mLocation;

    public JobOrders(String mJobId, String mType, String mPriority, String mStatus, String mLocation) {
        this.mJobId = mJobId;
        this.mType = mType;
        this.mPriority = mPriority;
        this.mStatus = mStatus;
        this.mLocation = mLocation;
    }

    public JobOrders(String mJobId, String mType, String mPriority,String mLocation){
        this.mJobId = mJobId;
        this.mType = mType;
        this.mPriority = mPriority;
        this.mLocation = mLocation;
    }

    public String getmJobId() {
        return mJobId;
    }

    public String getmType() {
        return mType;
    }

    public String getmPriority() {
        return mPriority;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmLocation() {
        return mLocation;
    }
}
