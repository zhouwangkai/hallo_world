package com.example.mytianqi;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentPost extends Fragment {
    EditText email;
    Button btn_confirm;
    EditText tv_key;
    String number;
    String cipher;
    SQLiteDatabase db;
    Random random=new Random();
    TextView point;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_post, container, false);
        email = view.findViewById(R.id.et_email);
        tv_key = view.findViewById(R.id.tv_key);
        btn_confirm = view.findViewById(R.id.btn_confirm);
        point=view.findViewById(R.id.point);

        db = SQLiteDatabase.openOrCreateDatabase(getActivity().getFilesDir().toString()
                + "/prefoin.db3", null);

        db.execSQL("create table if not exists my_account(_id integer"
                + " primary key,"
                + "news_email varchar(255),"
                + "news_head varchar(255),"
                + "news_name varchar(255),"
                + "news_key varchar(255))");
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = email.getText().toString();
                cipher = tv_key.getText().toString();
                Pattern p = Pattern.compile("^[a-zA-Z0-9]" +
                        "[\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
                Matcher m = p.matcher(number);
                if (m.matches()) {
                    int Max = 1000000;
                    int Min = 100000;
                    Toast.makeText(getActivity(), "正确", Toast.LENGTH_SHORT).show();
                    int match = (int) random.nextInt(Max) % (Max - Min + 1) + Min;
                    String s = String.valueOf(match);
                    insertData(db, number, cipher, s);
                    point.setText("您的账号为" + s);
                } else {
                    Toast.makeText(getActivity(), "错误", Toast.LENGTH_SHORT).show();
                }

            }


        });
        return view;
    }
    private void insertData(SQLiteDatabase db,String number,String cipher,String s){
        db.execSQL("insert into my_account(news_email,news_key,news_head) values(?,?,?)"
        ,new String[]{number,cipher,s});
    }
    public void onDestroy () {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

}
