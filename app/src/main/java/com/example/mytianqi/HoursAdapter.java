package com.example.mytianqi;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.ViewHolder> {
    private Context context;
    private List<Data> lists;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView temp;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temp = (TextView) itemView.findViewById(R.id.tv_temp);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            image=(ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

    public HoursAdapter(List<Data> lists,Context context) {
        this.lists = lists;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.line, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Data list=lists.get(position);
        holder.time.setText(list.getTime());
        holder.temp.setText(list.getTemp());
        holder.image.setImageResource(list.image);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


}
