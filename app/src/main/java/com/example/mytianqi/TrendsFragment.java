package com.example.mytianqi;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrendsFragment<lv_discuss> extends Fragment {
    ImageView add;
    ListView lv_discuss;
    TextView tv_user;
    List<Map<String,Object>> datas;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.trends_fragment, container, false);
        lv_discuss = view.findViewById(R.id.lv_discuss);
        tv_user = view.findViewById(R.id.tv_user);
        datas = new ArrayList<>();
        add = view.findViewById(R.id.iv_add);
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
        if (requestCode==1&&resultCode==10){
            Bundle bundle= data.getExtras();
            String text=bundle.getString("text");
           Map<String,Object> data_trends = new HashMap<>();
           data_trends.put("text",text);
           datas.add(data_trends);
           if (datas.size()>1){
               for (int i=datas.size()-1;i>=1;i--){
                   Map<String,Object> tem = null;
                   tem=datas.get(i);
                   datas.set(i,datas.get(i-1));
                   datas.set(i-1,tem);
               }
           }
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), datas, R.layout.trends_fragment_line,
                    new String[]{"text"}
                    , new int[]{R.id.tv_text});

            lv_discuss.setAdapter(adapter);

        }
    }




}
