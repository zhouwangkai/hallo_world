package com.example.mytianqi;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.RowSet;

import static com.example.mytianqi.R.id.*;

//implements TrendsAdapter.Callback
public class TrendsFragment extends Fragment {
    ImageView add;
    ListView lv_discuss;
    TextView tv_user;
    List<DataTrends> datas;
    SQLiteDatabase db;
     List<List<String>> functions ;
     List<String> function;

    int j = 0;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.trends_fragment, container, false);
        db = SQLiteDatabase.openOrCreateDatabase(getActivity().getFilesDir().toString()
                + "/my.db3", null);

        db.execSQL("create table if not exists my_table(_id integer"
                + " primary key,"
                + "news_data varchar(255),"
                + "news_good varchar(255),"
                + "news_function varchar(255))");
        lv_discuss = view.findViewById(R.id.lv_discuss);
        datas = new ArrayList<>();
        add = view.findViewById(iv_add);
        functions=new ArrayList<>();;
        Cursor cursor = db.rawQuery("select * from my_table", null);

        if (cursor.moveToFirst()) {
            do {
                DataTrends dataTrends = new DataTrends();
                String string = cursor.getString(cursor.getColumnIndex("news_data"));
                String number = cursor.getString(cursor.getColumnIndex("news_good"));
                dataTrends.setText(string);
                dataTrends.setGood(number);
                datas.add(dataTrends);
                j++;

            } while (cursor.moveToNext());
        }
        TrendsAdapter adapter = new TrendsAdapter(getActivity(), datas, db,functions);
        lv_discuss.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), DiscussActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 10) {
            Bundle bundle = data.getExtras();
            String text = bundle.getString("text");
            String good = "0";
            DataTrends data_trends = new DataTrends();
            data_trends.setGood(good);
            data_trends.setText(text);
            datas.add(data_trends);
            if (datas.size() > 1) {
                for (int i = datas.size() - 1; i >= 1; i--) {
                    DataTrends tem = null;
                    tem = datas.get(i);
                    datas.set(i, datas.get(i - 1));
                    datas.set(i - 1, tem);
                }
            }
            insertData(db, text);
            TrendsAdapter adapter = new TrendsAdapter(getActivity(), datas, db,functions);
            lv_discuss.setAdapter(adapter);
        }
    }


    private void insertData(SQLiteDatabase db, String text) {
        db.execSQL("insert into my_table(_id,news_data,news_good,news_function)values(?,?,0,null)"
                , new String[]{String.valueOf(j), text});
        j++;
    }
    public void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
