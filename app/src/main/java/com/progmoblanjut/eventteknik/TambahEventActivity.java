package com.progmoblanjut.eventteknik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

public class TambahEventActivity extends AppCompatActivity {

    private EditText namaEvent, tanggal, tanggalRapatPerdana, tempatRapatPerdana, tempatPelaksanaan, deskripsi;
    private Button tambah, batal;
    SQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_event);
        namaEvent = findViewById(R.id.NamaEventTambah);
        tanggal = findViewById(R.id.TanggalPelaksanaanTambah);
        tanggalRapatPerdana = findViewById(R.id.TanggalRapatPerdanaTambah);
        tempatRapatPerdana = findViewById(R.id.TempatRapatPerdanaTambah);
        tempatPelaksanaan = findViewById(R.id.TempatPelaksanaanTambah);
        deskripsi = findViewById(R.id.DeskripsiTambah);
        tambah = (Button) findViewById(R.id.btnSimpanTambah);
        batal = (Button) findViewById(R.id.btnBatalTambah);
        helper = new SQLiteHelper(this);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_event_acara = namaEvent.getText().toString();
                String tanggal_pelaksanaan_acara = tanggal.getText().toString();
                String tanggal_rapat_perdana_acara = tanggalRapatPerdana.getText().toString();
                String tempat_rapat_perdana_acara = tempatRapatPerdana.getText().toString();
                String tempat_pelaksanaan_acara = tempatPelaksanaan.getText().toString();
                String deskripsi_acara = deskripsi.getText().toString();

                if(TextUtils.isEmpty(nama_event_acara)) {
                    namaEvent.setError("Data tidak boleh kosong!");
                    namaEvent.requestFocus();
                } else if(TextUtils.isEmpty(tanggal_pelaksanaan_acara)) {
                    tanggal.setError("Data tidak boleh kosong!");
                    tanggal.requestFocus();
                } else if(TextUtils.isEmpty(tanggal_rapat_perdana_acara)) {
                    tanggalRapatPerdana.setError("Data tidak boleh kosong!");
                    tanggalRapatPerdana.requestFocus();
                } else if(TextUtils.isEmpty(tempat_rapat_perdana_acara)) {
                    tempatRapatPerdana.setError("Data tidak boleh kosong!");
                    tempatRapatPerdana.requestFocus();
                } else if(TextUtils.isEmpty(tempat_pelaksanaan_acara)) {
                    tempatPelaksanaan.setError("Data tidak boleh kosong!");
                    tempatPelaksanaan.requestFocus();
                } else if(TextUtils.isEmpty(deskripsi_acara)) {
                    deskripsi.setError("Data tidak boleh kosong!");
                    deskripsi.requestFocus();
                } else {
                    boolean isInsert = helper.insertEvent(nama_event_acara, tanggal_pelaksanaan_acara, tanggal_rapat_perdana_acara, tempat_pelaksanaan_acara, tempat_rapat_perdana_acara, deskripsi_acara);
                    if(isInsert) {
                        Toast.makeText(TambahEventActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        namaEvent.setText(null);
                        tanggal.setText(null);
                        tanggalRapatPerdana.setText(null);
                        tempatRapatPerdana.setText(null);
                        tempatPelaksanaan.setText(null);
                        deskripsi.setText(null);
                    } else {
                        Toast.makeText(TambahEventActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}