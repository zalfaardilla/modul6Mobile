package com.example.crudmahasiswa.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://pemrogramanmobile6.000webhostapp.com";
    private static Retrofit retrofit=null;

    public static final Retrofit getClient(){
        if (retrofit == null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
