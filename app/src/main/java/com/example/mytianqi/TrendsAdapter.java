package com.example.mytianqi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class TrendsAdapter extends BaseAdapter{
    private List<DataTrends> Lists;
    private LayoutInflater inflater;
    private SQLiteDatabase db;
    int j;
    List<Map<String,Object>> datas;
    Context context;

    public TrendsAdapter(Context context, List<DataTrends> lists,SQLiteDatabase db) {
        Lists = lists;
        inflater=LayoutInflater.from(context);
        this.db=db;
        this.context=context;

    }
    public int getCount() {
        return Lists.size();
    }

    @Override
    public Object getItem(int i) {
        return Lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder = new ViewHolder();
        view= inflater.inflate(R.layout.trends_fragment_line,null);
        view.setTag(holder);
        holder.good=view.findViewById(R.id.good);
        holder.text=view.findViewById(R.id.tv_text);
        holder.number=view.findViewById(R.id.number);
        holder.text.setText(Lists.get(i).getText());
        holder.listView=view.findViewById(R.id.discuss);
        holder.number.setText(Lists.get(i).getGood());
        holder.et_discuss=view.findViewById(R.id.et_discuss);
        holder.reply=view.findViewById(R.id.reply);
        datas=new ArrayList<>();
        final boolean[] k = {true};

        holder.good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s= (String) holder.number.getText();
                int a=Integer.parseInt(s);
                if (k[0] ==true) {
                    a = a + 1;
                    int img=R.mipmap.zan1;
                    holder.good.setImageResource(img);
                    k[0]=false;

                }else {
                    a = a - 1;
                    holder.good.setImageResource(R.mipmap.zan);
                    k[0]=true;
                }
                s=String.valueOf(a);
                holder.number.setText(s);
                ContentValues values=new ContentValues();
                values.put("news_good",s);
                db.execSQL("update my_table set news_good=? where _id=?",new String[]{s, String.valueOf(i)});
        }
        });

//        holder.reply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String m=holder.et_discuss.getText().toString();
//                Map<String, Object> data=new HashMap<>();
//                data.put("reply",m);
//                datas.add(data);
//                SimpleAdapter adapter=new SimpleAdapter(context,datas,R.layout.line_discuss,
//                        new String[]{"reply"}
//                        ,new int[]{R.id.textview});
//                holder.listView.setAdapter(adapter);
//            }
//});

        holder.good.setTag(i);
        return view;
    }
    public static class ViewHolder{
        ImageView good;
        TextView text,number;
        EditText et_discuss;
        Button reply;
        ListView listView;
    }




}