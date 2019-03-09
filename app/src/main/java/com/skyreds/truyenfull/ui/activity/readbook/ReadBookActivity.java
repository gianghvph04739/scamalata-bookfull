package com.skyreds.truyenfull.ui.activity.readbook;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.adapter.LoadMoreVerticalAdapter;
import com.skyreds.truyenfull.base.BaseActivity;
import com.skyreds.truyenfull.listener.OnLoadMoreListener;
import com.skyreds.truyenfull.networking.ApiEndpoint;
import com.skyreds.truyenfull.ui.activity.readbook.model.Chapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReadBookActivity extends BaseActivity implements ReadbookListener {

    Unbinder unbinder;
    @BindView(R.id.rv_menu)
    RecyclerView rv_menu;

    LoadMoreVerticalAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ArrayList<Chapter>> studentList;

    private String url;
    ReadbookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_read_book);

        setUnBinder(unbinder);
        setUp();


    }

    @Override
    protected void setUp() {
        ButterKnife.bind(this);
        presenter = new ReadbookPresenter(this, this);
        url = getIntent().getStringExtra("url") + ApiEndpoint.getEnpointPage(2);
        presenter.loadInfoBook(url);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        rv_menu.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        rv_menu.setLayoutManager(mLayoutManager);

        // create an Object for Adapter
//        adapter = new LoadMoreVerticalAdapter(studentList, rv_menu);
//
//        // set the adapter object to the Recyclerview
//        rv_menu.setAdapter(adapter);
        //  mAdapter.notifyDataSetChanged();


//        if (studentList.isEmpty()) {
//            rv_menu.setVisibility(View.GONE);
//            tvEmptyView.setVisibility(View.VISIBLE);
//
//        } else {
//            rv_menu.setVisibility(View.VISIBLE);
//            tvEmptyView.setVisibility(View.GONE);
//        }

//        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                //add null , so the adapter will check view_type and show progress bar at bottom
//                studentList.add(null);
//                adapter.notifyItemInserted(studentList.size() - 1);
//
//                Toast.makeText(ReadBookActivity.this, "loadmore", Toast.LENGTH_SHORT).show();

//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //   remove progress item
//                        studentList.remove(studentList.size() - 1);
//                        mAdapter.notifyItemRemoved(studentList.size());
//                        //add items one by one
//                        int start = studentList.size();
//                        int end = start + 20;
//
//                        for (int i = start + 1; i <= end; i++) {
//                            studentList.add(new Student("Student " + i, "AndroidStudent" + i + "@gmail.com"));
//                            mAdapter.notifyItemInserted(studentList.size());
//                        }
//                        mAdapter.setLoaded();
//                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
//                    }
//                }, 2000);

//            }
//        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onLoadListChapterSuccess(final ArrayList<Chapter> listChapter) {
        adapter = new LoadMoreVerticalAdapter(listChapter, rv_menu);

        // set the adapter object to the Recyclerview
        rv_menu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //add null , so the adapter will check view_type and show progress bar at bottom
//                studentList.add(listChapter);
//                adapter.notifyItemInserted(studentList.size() - 1);

                Toast.makeText(ReadBookActivity.this, "loadmore", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this, listChapter.size() + listChapter.get(0).getChapter(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadListChapterFailed(String message) {

    }
}
