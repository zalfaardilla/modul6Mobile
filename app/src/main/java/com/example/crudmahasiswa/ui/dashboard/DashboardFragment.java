package com.example.crudmahasiswa.ui.dashboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crudmahasiswa.R;
import com.example.crudmahasiswa.model.dataDashboard;
import com.example.crudmahasiswa.services.ApiClient;
import com.example.crudmahasiswa.services.ApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    private TextView tvTotalMahasiswa, tvMahasiswaTeknik, tvMahasiswaInformatika,
            tvLastUpdateTime, tvLastUpdateDate;
    private ApiService apiService;
    private ProgressDialog dialog;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        loadData();

        return root;
    }

    private void loadData() {
        dialog = new ProgressDialog(root.getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Loading ...");
        dialog.show();

        apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getdataDasboard().enqueue(new Callback<dataDashboard>() {
            @Override
            public void onResponse(Call<dataDashboard> call, Response<dataDashboard> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();

                    tvTotalMahasiswa.setText(response.body().getResult().getTotalMahasiswa());
                    tvMahasiswaTeknik.setText(response.body().getResult().getMahasiswaTeknik());
                    tvMahasiswaInformatika.setText(response.body().getResult().getMahasiswaInformatika());

                    String dateSQL = response.body().getResult().getLastUpdate();
                    String[] dateSQLSplit = dateSQL.split(" ");
                    String[] timeSQLSplit = dateSQLSplit[1].split(":");

                    tvLastUpdateTime.setText(timeSQLSplit[0] + ":" + timeSQLSplit[1]);
                    tvLastUpdateDate.setText(dateSQLSplit[0]);
                }
            }

            @Override
            public void onFailure(Call<dataDashboard> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTotalMahasiswa = view.findViewById(R.id.totalMahasiswa);
        tvMahasiswaTeknik = view.findViewById(R.id.totalMahasiswaTeknik);
        tvMahasiswaInformatika = view.findViewById(R.id.totalMahasiswaInformatika);
        tvLastUpdateTime = view.findViewById(R.id.lastUpdateTime);
        tvLastUpdateDate = view.findViewById(R.id.lastUpdateDate);
    }
}
