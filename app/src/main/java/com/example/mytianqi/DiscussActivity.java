package com.example.mytianqi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class DiscussActivity extends Activity {

    Button btn_bn1;
    Button btn_bn2;
    EditText user_text;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        user_text=findViewById(R.id.user_text);
        btn_bn1 = findViewById(R.id.btn_bn1);
        btn_bn2 = findViewById(R.id.btn_bn2);
        text=user_text.getText().toString();
        btn_bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle data=new Bundle();
//                data.putString("text",text);
//                Intent intent=new Intent();
//                intent.putExtras(data);
                setResult(10);
                finish();
            }
        });
        btn_bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(11);
                finish();
            }
        });
    }
}
