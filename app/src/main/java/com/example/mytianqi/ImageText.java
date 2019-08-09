package com.example.mytianqi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageText extends LinearLayout {
    private ImageView mImgView = null;
    private TextView mTextView = null;
    private Context mContext;

    public ImageText(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.imagetext, this, true);
        mContext = context;
        mImgView = (ImageView)findViewById(R.id.image);
        mTextView = (TextView)findViewById(R.id.text);
    }

    /*设置图片接口*/
    public void setImageResource(int resId){
        mImgView.setImageResource(resId);
    }

    /*设置文字接口*/
    public void setText(String str){
        mTextView.setText(str);
    }
    /*设置文字大小*/
    public void setTextSize(float size){
        mTextView.setTextSize(size);
    }
}
