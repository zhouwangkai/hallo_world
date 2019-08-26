package com.example.mytianqi;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class CeShi extends Activity {
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MyDatabaseHelper(this, "prefoin.db3",null, 1);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", 1);
        values.put("news_email", "1448002351@qq.com");
        values.put("news_key", "12438653");
        values.put("news_head", "1263718");
        long l = sqLiteDatabase.insert("prefoin", null, values);
    }


}
