package com.example.mytianqi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.util.List;
import java.util.zip.Inflater;

public class TrendsAdapter extends BaseAdapter implements View.OnClickListener {
    private List<DataTrends> Lists;
    private Callback callback;
    private LayoutInflater inflater;

    public interface Callback {
        public void click(View v);
    }

    public TrendsAdapter(Context context, List<DataTrends> lists) {
        Lists = lists;
        this.callback =callback;
        inflater=LayoutInflater.from(context);
    }
    public void onClick(View v){
        callback.click(v);
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
        holder.good=view.findViewById(R.id.good);
        holder.text=view.findViewById(R.id.tv_text);
        holder.number=view.findViewById(R.id.number);
        view.setTag(holder);
        holder.text.setText(Lists.get(i).getText());
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
            }
        });
        holder.good.setTag(i);
        return view;
    }
    public class ViewHolder{
        ImageView good;
        TextView text,number;
    }




}
