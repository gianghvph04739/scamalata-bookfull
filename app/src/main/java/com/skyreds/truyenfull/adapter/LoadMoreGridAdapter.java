package com.skyreds.truyenfull.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.view.activity.viewbook.ViewBookActivity;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.ArrayList;
import java.util.List;

public class LoadMoreGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public List<HotBook> mItemList;
    public Context context;
    public LoadMoreGridAdapter(Context context, List<HotBook> itemList) {
        mItemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_style1, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }
    }

    public void addItem(ArrayList<HotBook> lst) {
        mItemList.remove(mItemList.size()-1);
        this.mItemList.addAll(lst);
//        notifyItemRangeInserted(getItemCount()-lst.size(), getItemCount());
    }


    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == mItemList.size()-1 ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvChapter;
        public TextView tvName;
        public ImageView imgBook;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tvChapter = (TextView) itemView.findViewById(R.id.tv_chapter);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            imgBook = (ImageView) itemView.findViewById(R.id.img_book);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar1);
        }
    }

    public void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed
        viewHolder.progressBar.setIndeterminate(true);
    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        final HotBook item = mItemList.get(position);
        viewHolder.tvName.setText(item.getName());
        viewHolder.tvChapter.setText(item.getChapter());
        Glide.with(context).load(item.getPic_portairt()).into(viewHolder.imgBook);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(context, ViewBookActivity.class);
                i.putExtra("url",item.getLink_book());
                context.startActivity(i);
            }
        });

    }


}