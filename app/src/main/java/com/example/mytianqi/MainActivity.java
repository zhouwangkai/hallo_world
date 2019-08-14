package com.example.mytianqi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

// main
public class MainActivity extends AppCompatActivity {
    ImageView img1;
    ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = findViewById(R.id.iv_img1);
        img2 = findViewById(R.id.iv_img2);
        TianQiFragment tianqi = new TianQiFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_fragment, tianqi).commit();
        img1.setOnClickListener(l);
        img2.setOnClickListener(l);
    }

    View.OnClickListener l = new View.OnClickListener() {
        public void onClick(View view) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment f = null;
            switch (view.getId()) {
                case R.id.iv_img1:
                    f = new TianQiFragment();
                    break;
                case R.id.iv_img2:
                    f = new TrendsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.ll_fragment, f).commit();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==10) {
            Fragment fragment = new TrendsFragment();
            FragmentManager fmanger = getSupportFragmentManager();
            FragmentTransaction transaction = fmanger.beginTransaction();
            transaction.replace(R.id.ll_fragment, fragment);
            transaction.commit();
        }
        if (requestCode==1&&resultCode==11) {
            Fragment fragment = new TrendsFragment();
            FragmentManager fmanger = getSupportFragmentManager();
            FragmentTransaction transaction = fmanger.beginTransaction();
            transaction.replace(R.id.ll_fragment, fragment);
            transaction.commit();
        }
    }
}


