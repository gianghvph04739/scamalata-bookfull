package com.skyreds.truyenfull.ui.activity.listbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.adapter.AdapterListBook;
import com.skyreds.truyenfull.base.BaseActivity;
import com.skyreds.truyenfull.ui.fragment.feature.model.HotBook;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListBookActivity extends BaseActivity implements ListBookListener {
    private Unbinder unbinder;
    @BindView(R.id.toolbar_category)
    Toolbar toolbar;
    @BindView(R.id.rv_info)
    RecyclerView rvInfo;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    private String url="";
    private ListBookPresenter listener;
    int pagenumb = 2;
    String PAGE = "trang-";// + 3/
    String urlLoadmore;
    final ArrayList<HotBook> lstBook = new ArrayList<>();
    private AdapterListBook adapterListBook;
    private GridLayoutManager gridLayoutManager;

    private boolean isLoading = false;
    private int pastVisibleitem, visibleItemCount, totalItemCount, previousTotal = 0;
    private int view_threadsold = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_category_info);
        setUnBinder(unbinder);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        setUp();

    }

    @Override
    protected void setUp() {
        listener = new ListBookPresenter(this, this);
        gridLayoutManager = new GridLayoutManager(this,3);
        rvInfo.setHasFixedSize(true);
        rvInfo.setLayoutManager(gridLayoutManager);
        progressBar.setVisibility(View.VISIBLE);
        listener.loadCategory(url);
        rvInfo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = gridLayoutManager.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();
                pastVisibleitem = gridLayoutManager.findFirstVisibleItemPosition();

                if(dy>0){
                    if(isLoading){
                        if(totalItemCount>previousTotal){
                            isLoading=false;
                            previousTotal=totalItemCount;
                        }
                    }
                }
                if(!isLoading &&(totalItemCount-visibleItemCount)<=(pastVisibleitem+view_threadsold)){
                    progressBar.setVisibility(View.VISIBLE);
                    listener.loadMoreCategory(urlLoadmore);
                    isLoading=true;
                }
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onLoadCategorySucessful(final ArrayList<HotBook> lst) {
        progressBar.setVisibility(View.GONE);
        adapterListBook = new AdapterListBook(this,lst);
        rvInfo.setAdapter(adapterListBook);
        urlLoadmore = url + PAGE + pagenumb + "/";
    }

    @Override
    public void onLoadCategoryFailed(String message) {

    }

    @Override
    public void onLoadMoreCategorySucessful(final ArrayList<HotBook> lst) {
        pagenumb++;
        urlLoadmore = url + PAGE + pagenumb + "/";
        progressBar.setVisibility(View.GONE);
        adapterListBook.addDataLoadmore(lst);
        Log.e("page:",pagenumb+"");
    }

    @Override
    public void onLoadMoreCategoryFailed(String message) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        setAdapter();
                        swipeRefresh.setRefreshing(false);
                    }
                }, 5000);
            }
        });
    }

}
