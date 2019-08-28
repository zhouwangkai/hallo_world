package com.example.mytianqi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Map;

public class FragmentLanding extends Fragment {
    EditText et_username;
    EditText tv_passkey;
    Button btn_landing;
    SQLiteDatabase db;
    String account;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landing, container, false);
        et_username = view.findViewById(R.id.et_username);
        tv_passkey = view.findViewById(R.id.tv_passkey);
        btn_landing = view.findViewById(R.id.btn_landing);
        MyDatabaseHelper  dbHelper = new MyDatabaseHelper(getActivity(), "prefoin.db3", null, 1);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("prefoin", null, null, null, null, null, null);
        btn_landing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et_username.getText().toString();
                String m=tv_passkey.getText().toString();
                Cursor cursor1=db.rawQuery("select * from prefoin where news_head like ?",new String[]{s});
                while (cursor1.moveToNext()) {
                    String key = cursor1.getString(cursor1.getColumnIndex("news_key"));
                    account=cursor1.getString(cursor1.getColumnIndex("news_head"));
                    if (key.equals(m)){
                        Log.d("tag","通过");
                        ActivityLand user=(ActivityLand)getActivity();
                        Bundle data = new Bundle();
                        data.putString("account", account);
                        Intent intent = new Intent();
                        intent.putExtras(data);
                        user.setResult(10, intent);
                        user.finish();
                    }
                }

            }
            private SQLiteQueryBuilder getWritableDatabase() {
                return null;
            }
        });

        return view;
    }
}
