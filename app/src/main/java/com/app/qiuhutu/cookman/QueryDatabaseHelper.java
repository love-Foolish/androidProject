package com.app.qiuhutu.cookman;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class QueryDatabaseHelper extends SQLiteOpenHelper{

    private static final String TB_MENU_QUERYE = "create table tb_menu_query (" +
            "menuid text, " +
            "name text, " +
            "img text, " +
            "ingredients text, " +
            "summary text, " +
            "method_id text)";

    private static String TB_MENU_METHOD = "create table tb_menu_method (" +
            "menu_id text, " +
            "img text, " +
            "step text)";

    private static String TB_MENU_SEARCH = "create table tb_menu_search (" +
            "menu_search_id text, " +
            "cur_page text, " +
            "total text, " +
            "menu_search_result_id text)";

    private static String TB_SEARCH_RESULT = "create table tb_menu_search_result (" +
            "menu_search_result_id text, " +
            "menu_id text, name text, " +
            "thumbnail text)";

    private static String TB_CATEGORY_NAME = "create table tb_category_name (" +
            "category_name_id text, " +
            "ctg_id text, name text, " +
            "child_name_id)";

    private static String TB_CATEGORY_CHILD_NAME = "create table (" +
            "category_child_name_id text, " +
            "ctg_id text, name text, " +
            "parent_id text)";

    private static String TB_COLLECTION_USER = "create table (" +
            "user_id text, " +
            "list_id text)";

    private static String TB_COLLECTION_LIST = "create table (" +
            "list_id text, " +
            "menu_id text)";


    private Context mContext;


    public QueryDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TB_MENU_QUERYE);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
