package com.skyreds.truyenfull.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.listener.OnLoadMoreListener;
import com.skyreds.truyenfull.view.activity.listbook.viewholder.LoadingViewHolder;
import com.skyreds.truyenfull.view.activity.listbook.viewholder.UserViewHolder;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem,
            totalItemCount;
    private Context context;
    private ArrayList<HotBook> lst;
    private RecyclerView mRecyclerView;

    public UserAdapter(RecyclerView rv, Context context, ArrayList<HotBook> lst) {
        this.context = context;
        this.lst = lst;
        this.mRecyclerView = rv;

        final GridLayoutManager linearLayoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return lst.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_style1, parent, false);
            return new UserViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            HotBook item = lst.get(position);
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.tvName.setText(item.getName());
            userViewHolder.tvChapter.setText(item.getChapter());
            Glide.with(context).load(item.getPic_portairt()).into(userViewHolder.imgBook);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return lst == null ? 0 : lst.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
}