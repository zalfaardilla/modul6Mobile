package com.example.crudmahasiswa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataDashboard {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private resultDashboard result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public resultDashboard getResult() {
        return result;
    }

    public void setResult(resultDashboard result) {
        this.result = result;
    }
}
