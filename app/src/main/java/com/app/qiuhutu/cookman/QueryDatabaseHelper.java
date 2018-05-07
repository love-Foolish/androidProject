package com.app.qiuhutu.cookman;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QueryDatabaseHelper extends SQLiteOpenHelper{

    private static final String CREATE_QUERYDATABASE = "create table MenuQuery = (" +
            "id integer primary key autoincrement, " +
            "msg text, " +
            "ctgId)";

    public QueryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
