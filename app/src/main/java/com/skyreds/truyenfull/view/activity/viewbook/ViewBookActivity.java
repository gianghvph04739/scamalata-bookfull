package com.skyreds.truyenfull.view.activity.viewbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.base.BaseActivity;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.ArrayList;

public class ViewBookActivity extends BaseActivity implements ViewBookListener {
    private ViewBookPresenter presenter;
    private String urlBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);


        setUp();

    }

    @Override
    protected void setUp() {
        Intent intent = getIntent();
        urlBook = intent.getStringExtra("url");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle("Chi tiết truyện");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        presenter = new ViewBookPresenter(this,this);
        presenter.loadInfoBook(urlBook);
    }

    @Override
    public void onLoadInfoSucessful(ArrayList<HotBook> lst) {

    }

    @Override
    public void onLoadInfoFailed(String message) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
