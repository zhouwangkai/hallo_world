package com.example.mytianqi;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentPresonal extends Fragment {
    String account;
    SQLiteDatabase db;
    TextView name;
    String username;
    TextView information;
    TextView fit;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        RelativeLayout land;
        ImageView head;
        name = view.findViewById(R.id.tv_name);
        information=view.findViewById(R.id.tv_information);
        fit=view.findViewById(R.id.fit);
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getActivity(), "prefoin.db3", null, 1);
        db = dbHelper.getWritableDatabase();
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ActivityLand.class);
                startActivityForResult(intent, 0);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent();
              intent.setClass(getActivity(),ActivityInformation.class);
              startActivity(intent);
            }
        });
        fit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),ActovotyFit.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 10) {
            Bundle bundle = data.getExtras();
            account = bundle.getString("account");
            Cursor cursor = db.rawQuery("select * from prefoin where news_head like ?", new String[]{account});
            while (cursor.moveToNext()) {
                username = cursor.getString(cursor.getColumnIndex("news_name"));
            }
            name.setText(username);
        }
    }
}
