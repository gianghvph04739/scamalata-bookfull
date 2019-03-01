package com.skyreds.truyenfull.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.view.fragment.category.model.Category;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder>{

    private final List<Category> lst;
    private final Context mContext;
    private OnItemClickListener listener;

    public AdapterCategory(Context context, List<Category> comicList) {
        this.lst = comicList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_category, parent, false);
        return new ViewHolder(itemView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvName;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_category);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Category item = lst.get(position);
        holder.tvName.setText(item.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickitem(item.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public interface OnItemClickListener {
        void onClickitem(String url);
    }

    public void setOnItemClicked(OnItemClickListener onItemClicked) {
        this.listener=onItemClicked;
    }
}