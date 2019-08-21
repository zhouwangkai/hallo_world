package com.example.mytianqi;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AdapterDiscuss extends BaseAdapter {
    private List<String> function;
    Context context;
    TextView textView;
    private LayoutInflater inflater;
    public AdapterDiscuss(List<String> function,Context context) {
        this.function = function;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return function.size();
    }

    @Override
    public Object getItem(int i) {
        return function.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
//    public void addData(String item){
//        function.add(item);
//        this.notifyDataSetChanged();
//    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final TrendsAdapter.ViewHolder holder = new TrendsAdapter.ViewHolder();
        view= inflater.inflate(R.layout.line_discuss,null);
        textView=view.findViewById(R.id.textview);
        String s=getItem(i).toString();
        textView.setText(s);
        return view;
    }


}
