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

import java.util.List;

public class AdapterBookStyle2 extends RecyclerView.Adapter<AdapterBookStyle2.ViewHolder> {

    private final List<HotBook> lst;
    private final Context mContext;

    public AdapterBookStyle2(Context context, List<HotBook> comicList) {
        this.lst = comicList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_style2, parent, false);
        return new ViewHolder(itemView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvChapter;
        final TextView tvName;
        final TextView tvAuthor;
        final ImageView thumbnail;

        ViewHolder(View itemView) {
            super(itemView);
            tvChapter = itemView.findViewById(R.id.tv_chapter);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            thumbnail = itemView.findViewById(R.id.img_thumb);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final HotBook item = lst.get(position);
        holder.tvChapter.setText(item.getChapter());
        holder.tvName.setText(item.getName());
        holder.tvAuthor.setText(item.getAuthor());
        Glide.with(mContext).load(item.getPic_portairt()).into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(mContext, ViewBookActivity.class);
                i.putExtra("url",item.getLink_book());
                i.putExtra("name",item.getName());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lst.size();
    }


}