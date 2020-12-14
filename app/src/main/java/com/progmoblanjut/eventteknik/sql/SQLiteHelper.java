package com.progmoblanjut.eventteknik.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_kepanitiaan";
    private static final String TABEL_EVENT = "event";
    private static final String KOLOM_ID = "id";
    private static final String KOLOM_NAMA_EVENT = "nama_event";
    private static final String KOLOM_TANGGAL_PELAKSANAAN = "tanggal_pelaksanaan";
    private static final String KOLOM_TANGGAL_RAPAT_PERDANA = "tanggal_rapat_perdana";
    private static final String KOLOM_TEMPAT_PELAKSANAAN = "tempat_pelaksanaan";
    private static final String KOLOM_TEMPAT_RAPAT_PERDANA = "tempat_rapat_perdana";
    private static final String KOLOM_DESKRIPSI = "deskripsi";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABEL_EVENT + " (" +
                KOLOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KOLOM_NAMA_EVENT + " TEXT, " +
                KOLOM_TANGGAL_PELAKSANAAN + " TEXT, " +
                KOLOM_TANGGAL_RAPAT_PERDANA + " TEXT, " +
                KOLOM_TEMPAT_PELAKSANAAN + " TEXT," +
                KOLOM_TEMPAT_RAPAT_PERDANA + " TEXT," +
                KOLOM_DESKRIPSI + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_EVENT);
    }

    public boolean insertEvent(String nama_event, String tanggal_pelaksanaan, String tanggal_rapat_perdana, String tempat_pelaksanaan, String tempat_rapat_perdana, String deskripsi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KOLOM_NAMA_EVENT, nama_event);
        values.put(KOLOM_TANGGAL_PELAKSANAAN, tanggal_pelaksanaan);
        values.put(KOLOM_TANGGAL_RAPAT_PERDANA, tanggal_rapat_perdana);
        values.put(KOLOM_TEMPAT_PELAKSANAAN, tempat_pelaksanaan);
        values.put(KOLOM_TEMPAT_RAPAT_PERDANA, tempat_rapat_perdana);
        values.put(KOLOM_DESKRIPSI, deskripsi);
        long result = db.insert(TABEL_EVENT, null, values);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABEL_EVENT, null);
    }

    public boolean updateData(String id, String nama_event, String tanggal_pelaksanaan, String tanggal_rapat_perdana, String tempat_pelaksanaan, String tempat_rapat_perdana, String deskripsi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KOLOM_ID, id);
        values.put(KOLOM_NAMA_EVENT, nama_event);
        values.put(KOLOM_TANGGAL_PELAKSANAAN, tanggal_pelaksanaan);
        values.put(KOLOM_TANGGAL_RAPAT_PERDANA, tanggal_rapat_perdana);
        values.put(KOLOM_TEMPAT_PELAKSANAAN, tempat_pelaksanaan);
        values.put(KOLOM_TEMPAT_RAPAT_PERDANA, tempat_rapat_perdana);
        values.put(KOLOM_DESKRIPSI, deskripsi);
        db.update(TABEL_EVENT, values, KOLOM_ID + " =? ", new String[]{id});
        return true;
    }

}
