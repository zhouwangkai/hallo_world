package com.example.mytianqi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TrendsFragment extends Fragment {
    ImageView add;
    ListView tv_discuss;
    TextView tv_user;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trends_fragment, container, false);
        tv_discuss = view.findViewById(R.id.tv_discuss);
        tv_user = view.findViewById(R.id.tv_user);
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
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==1&&resultCode==10){
//            String text= (String) getArguments().get("text");
//            tv_user.setText(text);
//        }
//    }
}
