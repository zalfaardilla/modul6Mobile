package com.example.crudmahasiswa.ui.insert;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crudmahasiswa.R;
import com.example.crudmahasiswa.model.resultResponse;
import com.example.crudmahasiswa.services.ApiClient;
import com.example.crudmahasiswa.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertFragment extends Fragment {
    private EditText edtNIM, edtNama, edtFakultas, edtJurusan;
    private Button btnSubmit;
    private ProgressDialog progress;
    private ApiService apiService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_insert, container, false);

        edtNIM = root.findViewById(R.id.edt_nim);
        edtNama = root.findViewById(R.id.edt_nama);
        edtFakultas = root.findViewById(R.id.edt_fakultas);
        edtJurusan = root.findViewById(R.id.edt_jurusan);
        btnSubmit = root.findViewById(R.id.btn_submit);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSubmit.setOnClickListener(v -> {
            progress = new ProgressDialog(getContext());
            progress.setCancelable(false);
            progress.setMessage("Loading ...");
            progress.show();

            apiService = ApiClient.getClient().create(ApiService.class);
            Call<resultResponse> resultInsertCall = apiService.daftar(edtNIM.getText().toString(),
                    edtNama.getText().toString(), edtFakultas.getText().toString(),
                    edtJurusan.getText().toString());
            resultInsertCall.enqueue(new Callback<resultResponse>() {
                @Override
                public void onResponse(Call<resultResponse> call, Response<resultResponse> response) {
                    progress.dismiss();
                    String status = response.body().getStatus();
                    String message = response.body().getMessage();

                    if (status.equals("ok")){
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<resultResponse> call, Throwable t) {
                    progress.dismiss();
                    Toast.makeText(getContext(), "GAGAL DITAMBAHKAN", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
