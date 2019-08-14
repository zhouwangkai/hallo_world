package com.example.mytianqi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TrendsFragment extends Fragment {
    ImageView add;
    ListView tv_discuss;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trends_fragment, container, false);
        tv_discuss=view.findViewById(R.id.tv_discuss);
        add=view.findViewById(R.id.iv_add);
       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent();
               intent.setClass(getActivity(),DiscussActivity.class);
               startActivityForResult(intent,1);
           }
       });
        return view;
    }

}
