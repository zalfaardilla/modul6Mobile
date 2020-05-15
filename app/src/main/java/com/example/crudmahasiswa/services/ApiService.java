package com.example.crudmahasiswa.services;

import com.example.crudmahasiswa.model.dataDashboard;
import com.example.crudmahasiswa.model.dataMahasiswa;
import com.example.crudmahasiswa.model.resultResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("read.php")
    Call<dataMahasiswa> getdataMahasiswa();

    @FormUrlEncoded
    @POST("insert.php")
    Call<resultResponse> daftar(@Field("nim") String nim,
                                @Field("nama") String nama,
                                @Field("fakultas") String fakultas,
                                @Field("jurusan") String jurusan);

    @FormUrlEncoded
    @POST("update.php")
    Call<resultResponse> update(@Field("id") String id,
                                @Field("nim") String nim,
                                @Field("nama") String nama,
                                @Field("fakultas") String fakultas,
                                @Field("jurusan") String jurusan);

    @FormUrlEncoded
    @POST("delete.php")
    Call<resultResponse> delete(@Field("id") String id);

    @GET("dashboard.php")
    Call<dataDashboard> getdataDasboard();
}
