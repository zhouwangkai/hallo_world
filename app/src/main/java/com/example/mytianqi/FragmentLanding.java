package com.example.mytianqi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Map;

public class FragmentLanding extends Fragment {
    EditText et_username;
    EditText tv_passkey;
    Button btn_landing;
    SQLiteDatabase db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landing, container, false);
        et_username=view.findViewById(R.id.et_username);
        tv_passkey=view.findViewById(R.id.tv_passkey);
        btn_landing=view.findViewById(R.id.btn_landing);
        db = SQLiteDatabase.openOrCreateDatabase(getActivity().getFilesDir().toString()
                + "/prefoin.db3", null);
        btn_landing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=et_username.getText().toString();
                Cursor cursor=db.rawQuery("select * from my_account where news_head=?",new String[]{s});
                ArrayList<Map<String,String>> map=new ArrayList<Map<String,String>>();
//                if (){
//                    Toast.makeText(getActivity(),"该账号不存在",Toast.LENGTH_SHORT).show();
                Cursor b=db.rawQuery("select * from my_account ",null);
//                }
            }
        });
        return view;
    }
}
