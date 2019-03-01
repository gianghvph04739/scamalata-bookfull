package com.skyreds.truyenfull.view.activity.listbook.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyreds.truyenfull.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView tvChapter;
    public TextView tvName;
    public ImageView imgBook;
    public UserViewHolder(View itemView) {
        super(itemView);
        tvChapter = (TextView) itemView.findViewById(R.id.tv_chapter);
        tvName = (TextView) itemView.findViewById(R.id.tv_name);
        imgBook = (ImageView) itemView.findViewById(R.id.img_book);
    }
}