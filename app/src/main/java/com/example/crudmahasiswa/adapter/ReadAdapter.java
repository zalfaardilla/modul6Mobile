package com.example.crudmahasiswa.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudmahasiswa.R;
import com.example.crudmahasiswa.model.resultMahasiswa;
import com.example.crudmahasiswa.model.resultResponse;
import com.example.crudmahasiswa.services.ApiClient;
import com.example.crudmahasiswa.services.ApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ViewHolder> {
    private ArrayList<resultMahasiswa> resultMahasiswas;
    private View view;

    public ReadAdapter(ArrayList<resultMahasiswa> resultMahasiswas) {
        this.resultMahasiswas = resultMahasiswas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNama.setText(resultMahasiswas.get(position).getNama());
        holder.tvNim.setText(resultMahasiswas.get(position).getNim());
        holder.tvFakultas.setText(resultMahasiswas.get(position).getFakultas());
        holder.tvJurusan.setText(resultMahasiswas.get(position).getJurusan());

        holder.itemView.setOnLongClickListener(v -> {
            final Dialog dialog;
            dialog = new Dialog(view.getContext());
            dialog.setContentView(R.layout.dialog_update);
            dialog.show();

            final EditText edtID = dialog.findViewById(R.id.edtID);
            final EditText edtNIM = dialog.findViewById(R.id.edtNIM);
            final EditText edtNama = dialog.findViewById(R.id.edtNama);
            final EditText edtFakultas = dialog.findViewById(R.id.edtFakultas);
            final EditText edtJurusan = dialog.findViewById(R.id.edtJurusan);
            final Button btnUpdate = dialog.findViewById(R.id.btUpdate);
            final Button btnDelete = dialog.findViewById(R.id.btDelete);

            edtID.setText(resultMahasiswas.get(position).getId());
            edtNIM.setText(resultMahasiswas.get(position).getNim());
            edtNIM.setEnabled(false);
            edtNama.setText(resultMahasiswas.get(position).getNama());
            edtFakultas.setText(resultMahasiswas.get(position).getFakultas());
            edtJurusan.setText(resultMahasiswas.get(position).getJurusan());

            btnUpdate.setOnClickListener(v1 -> {
                updateData(new resultMahasiswa(edtID.getText().toString(), edtNIM.getText().toString(),
                        edtNama.getText().toString(), edtFakultas.getText().toString(),
                        edtJurusan.getText().toString()));
                dialog.dismiss();
            });

            btnDelete.setOnClickListener(v12 -> {
                deleteData(edtID.getText().toString());
                dialog.dismiss();
            });

            // set size dialog
            Window window = dialog.getWindow();
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            return true;
        });
    }

    private void deleteData(String id) {
        ProgressDialog dialog;
        dialog = new ProgressDialog(view.getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Loading ...");
        dialog.show();

        ApiService apiService;

        apiService = ApiClient.getClient().create(ApiService.class);
        Call<resultResponse> resultResponseCall = apiService.delete(id);
        resultResponseCall.enqueue(new Callback<resultResponse>() {
            @Override
            public void onResponse(Call<resultResponse> call, Response<resultResponse> response) {
                String status = response.body().getStatus();
                String message = response.body().getMessage();
                if (status.equals("ok")) {
                    Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    // kalau sukses pindah ke fragment utama
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    ((BottomNavigationView) activity.findViewById(R.id.nav_view)).setSelectedItemId(R.id.navigation_insert);
                } else {
                    dialog.dismiss();
                    Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<resultResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(view.getContext(), "GAGAL DELETE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateData(resultMahasiswa resultMahasiswa) {
        ProgressDialog dialog;
        dialog = new ProgressDialog(view.getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Loading ...");
        dialog.show();

        ApiService apiService;

        apiService = ApiClient.getClient().create(ApiService.class);
        Call<resultResponse> resultInsertCall = apiService.update(resultMahasiswa.getId(),
                resultMahasiswa.getNim(), resultMahasiswa.getNama(), resultMahasiswa.getFakultas(),
                resultMahasiswa.getJurusan());
        resultInsertCall.enqueue(new Callback<resultResponse>() {
            @Override
            public void onResponse(Call<resultResponse> call, Response<resultResponse> response) {
                String status = response.body().getStatus();
                String message = response.body().getMessage();
                if (status.equals("ok")) {
                    Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    // kalau sukses pindah ke fragment utama
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    ((BottomNavigationView) activity.findViewById(R.id.nav_view)).setSelectedItemId(R.id.navigation_insert);
                } else {
                    dialog.dismiss();
                    Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<resultResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(view.getContext(), "GAGAL DIUPDATE", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return resultMahasiswas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvNim, tvFakultas, tvJurusan;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNim = itemView.findViewById(R.id.tv_nim);
            tvFakultas = itemView.findViewById(R.id.tv_fakultas);
            tvJurusan = itemView.findViewById(R.id.tv_jurusan);
        }
    }
}
