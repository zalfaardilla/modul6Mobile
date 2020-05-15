package com.example.crudmahasiswa.ui.read;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudmahasiswa.R;
import com.example.crudmahasiswa.adapter.ReadAdapter;
import com.example.crudmahasiswa.model.dataMahasiswa;
import com.example.crudmahasiswa.model.resultMahasiswa;
import com.example.crudmahasiswa.services.ApiClient;
import com.example.crudmahasiswa.services.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadFragment extends Fragment {
    private ReadAdapter adapter;
    private RecyclerView recyclerView;
    private ApiService apiService;
    private ArrayList<resultMahasiswa> resultMahasiswas;
    ProgressDialog dialog;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_read, container, false);
        recyclerView = root.findViewById(R.id.rv_dataMahasiswa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        loadData();

        return root;
    }

    private void loadData() {
        dialog = new ProgressDialog(root.getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Loading ...");
        dialog.show();

        apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getdataMahasiswa().enqueue(new Callback<dataMahasiswa>() {
            @Override
            public void onResponse(Call<dataMahasiswa> call, Response<dataMahasiswa> response) {
                resultMahasiswas = new ArrayList<>();
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    for (resultMahasiswa resultMahasiswa : response.body().getResult()) {
                        resultMahasiswas.add(resultMahasiswa);
                    }
                }
                adapter = new ReadAdapter(resultMahasiswas);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<dataMahasiswa> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }


}
