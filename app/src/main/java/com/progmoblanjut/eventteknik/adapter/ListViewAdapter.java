package com.progmoblanjut.eventteknik.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.progmoblanjut.eventteknik.EditEventActivity;
import com.progmoblanjut.eventteknik.R;
import com.progmoblanjut.eventteknik.sql.DataEventKepanitiaan;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<DataEventKepanitiaan> listKepanitiaan;
    private Context context;
    TextView nama, tanggal, tanggalRapatPerdana, tempat, tempatRapatPerdana, deskripsi;
    LinearLayout layout;

    public ListViewAdapter(List<DataEventKepanitiaan> listKepanitiaan, Context context) {
        this.listKepanitiaan = listKepanitiaan;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listKepanitiaan.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        nama = v.findViewById(R.id.NamaAcaraList);
        tanggal = v.findViewById(R.id.TanggalPelaksanaanList);
        tanggalRapatPerdana = v.findViewById(R.id.TanggalRapatPerdanaList);
        tempat = v.findViewById(R.id.TempatPelaksanaanList);
        tempatRapatPerdana = v.findViewById(R.id.TempatRapatPerdanaList);
        deskripsi = v.findViewById(R.id.DeskripsiList);
        layout = v.findViewById(R.id.linearLayout);

        nama.setText(listKepanitiaan.get(position).getNama_event());
        tanggal.setText(listKepanitiaan.get(position).getTanggal_pelaksanaan());
        tanggalRapatPerdana.setText(listKepanitiaan.get(position).getTanggal_rapat_perdana());
        tempat.setText(listKepanitiaan.get(position).getTempat_pelaksanaan());
        tempatRapatPerdana.setText(listKepanitiaan.get(position).getTempat_rapat_perdana());
        deskripsi.setText(listKepanitiaan.get(position).getDeskripsi());

        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, EditEventActivity.class);
                intent.putExtra("id", listKepanitiaan.get(position).getId());
                intent.putExtra("nama_event", listKepanitiaan.get(position).getNama_event());
                intent.putExtra("tanggal_rapat_perdana", listKepanitiaan.get(position).getTanggal_rapat_perdana());
                intent.putExtra("tanggal_pelaksanaan", listKepanitiaan.get(position).getTanggal_pelaksanaan());
                intent.putExtra("tempat_pelaksanaan", listKepanitiaan.get(position).getTempat_pelaksanaan());
                intent.putExtra("tempat_rapat_perdana", listKepanitiaan.get(position).getTempat_rapat_perdana());
                intent.putExtra("deskripsi", listKepanitiaan.get(position).getDeskripsi());
                context.startActivity(intent);
                return true;
            }
        });
        return v;
    }
}
