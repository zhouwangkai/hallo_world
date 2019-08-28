package com.example.mytianqi;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityLand extends AppCompatActivity {
    TextView landing;
    TextView post;

SQLiteDatabase db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
        landing = findViewById(R.id.landing);
        post = findViewById(R.id.post);
        landing.setOnClickListener(l);
        post.setOnClickListener(l);
        FragmentLanding fragmentLanding = new FragmentLanding();
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_interface, fragmentLanding).commit();


    }


    View.OnClickListener l = new View.OnClickListener() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = null;

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.landing:
                    f = new FragmentLanding();
                    break;
                case R.id.post:
                    f = new FragmentPost();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.ll_interface, f).commit();
        }
    };
}



