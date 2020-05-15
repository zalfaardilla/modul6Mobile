package com.example.crudmahasiswa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class resultMahasiswa {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("fakultas")
    @Expose
    private String fakultas;
    @SerializedName("jurusan")
    @Expose
    private String jurusan;

    public resultMahasiswa(String id, String nim, String nama, String fakultas, String jurusan) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.fakultas = fakultas;
        this.jurusan = jurusan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
