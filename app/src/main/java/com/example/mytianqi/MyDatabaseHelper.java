package com.example.mytianqi;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE_SQL =
            "create table prefoin(_id integer"
                                   + " primary key autoincrement,"
                                    +"news_email varchar(255),"
                                    +"news_head varchar(255),"
                                    +"news_name varchar(255),"
                                    +"news_key varchar(255))";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("onupdata called"+i+"--->"+i1);
    }
}
