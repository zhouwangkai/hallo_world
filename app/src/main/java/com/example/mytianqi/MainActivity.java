package com.example.mytianqi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bn2;
    Button bn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn1 = findViewById(R.id.bn1);
        bn2 = findViewById(R.id.bn2);
        TianQiFragment tianqi = new TianQiFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.r_layout, tianqi).commit();
        bn1.setOnClickListener(l);
        bn2.setOnClickListener(l);

    }

    View.OnClickListener l = new View.OnClickListener() {
        public void onClick(View view) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment f = null;
            switch (view.getId()) {
                case R.id.bn1:
                    f = new TianQiFragment();
                    break;
                case R.id.bn2:
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.r_layout,f).commit();
        }

    };



}



