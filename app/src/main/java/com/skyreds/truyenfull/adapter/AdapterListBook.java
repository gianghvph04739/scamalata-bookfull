package com.skyreds.truyenfull.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.ui.activity.viewbook.ViewBookActivity;
import com.skyreds.truyenfull.ui.fragment.feature.model.HotBook;

import java.util.ArrayList;

public class AdapterListBook extends RecyclerView.Adapter<AdapterListBook.MyViewHolder> {

    private ArrayList<HotBook> lst;
    private Context context;

    public AdapterListBook(Context context, ArrayList<HotBook> lst) {
        this.lst = lst;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_style1,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final HotBook item = lst.get(position);
        holder.tvName.setText(item.getName());
        holder.tvChapter.setText(item.getChapter());
        Glide.with(context).load(item.getPic_portairt()).into(holder.imgBook);
        holder.imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(context, ViewBookActivity.class);
                i.putExtra("url",item.getLink_book());
                i.putExtra("name",item.getName());
                context.startActivity(i);
            }
        });
    }

    public void addDataLoadmore(ArrayList<HotBook> lsts){
        lst.addAll(lsts);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvChapter;
        public TextView tvName;
        public ImageView imgBook;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvChapter = (TextView) itemView.findViewById(R.id.tv_chapter);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            imgBook = (ImageView) itemView.findViewById(R.id.img_book);
        }
    }
}