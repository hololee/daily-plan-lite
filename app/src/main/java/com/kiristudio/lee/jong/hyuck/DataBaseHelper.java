package com.kiristudio.lee.jong.hyuck;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jong on 2015-09-02.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    final String TABLE_NAME = "dataes";


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists " + TABLE_NAME + " (_id integer PRIMARY KEY autoincrement, Date integer, Content string)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
