package com.example.crudmahasiswa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class resultDashboard {
    @SerializedName("totalMahasiswa")
    @Expose
    private String totalMahasiswa;
    @SerializedName("mahasiswaTeknik")
    @Expose
    private String mahasiswaTeknik;
    @SerializedName("mahasiswaInformatika")
    @Expose
    private String mahasiswaInformatika;
    @SerializedName("lastUpdate")
    @Expose
    private String lastUpdate;

    public String getTotalMahasiswa() {
        return totalMahasiswa;
    }

    public void setTotalMahasiswa(String totalMahasiswa) {
        this.totalMahasiswa = totalMahasiswa;
    }

    public String getMahasiswaTeknik() {
        return mahasiswaTeknik;
    }

    public void setMahasiswaTeknik(String mahasiswaTeknik) {
        this.mahasiswaTeknik = mahasiswaTeknik;
    }

    public String getMahasiswaInformatika() {
        return mahasiswaInformatika;
    }

    public void setMahasiswaInformatika(String mahasiswaInformatika) {
        this.mahasiswaInformatika = mahasiswaInformatika;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
