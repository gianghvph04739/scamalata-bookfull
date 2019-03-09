package com.skyreds.truyenfull.ui.activity.viewbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.base.BaseActivity;
import com.skyreds.truyenfull.ui.activity.readbook.ReadBookActivity;
import com.skyreds.truyenfull.ui.activity.viewbook.model.InfoBookView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ViewBookActivity extends BaseActivity implements ViewBookListener {
    private ViewBookPresenter presenter;
    private String urlBook,nameBook;
    private Unbinder unbinder;

    @BindView(R.id.row_author)
    TextView tv_author;
    @BindView(R.id.row_category)
    TextView tv_category;
    @BindView(R.id.row_name)
    TextView tv_name;
    @BindView(R.id.row_source)
    TextView tv_source;
    @BindView(R.id.tv_descriptions)
    TextView tv_descriptions;

    @BindView(R.id.row_status)
    TextView tv_status;
    @BindView(R.id.row_thumb_view)
    ImageView img_thumb;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_view_book);



        setUp();

    }

    @Override
    protected void setUp() {
        ButterKnife.bind(this);
        setUnBinder(unbinder);
        Intent intent = getIntent();
        urlBook = intent.getStringExtra("url");
        nameBook = intent.getStringExtra("name");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle("Chi tiết truyện");

        presenter = new ViewBookPresenter(this,this);
        presenter.loadInfoBook(urlBook);
    }

    @OnClick(R.id.btnRead)
    void onReadClick(){
        Intent i = new Intent(this, ReadBookActivity.class);
        i.putExtra("url",urlBook);
        startActivity(i);
    }

    @Override
    public void onLoadInfoSucessful(InfoBookView infoBookView) {
        tv_author.setText(infoBookView.author);
        tv_category.setText(infoBookView.getCategory());
        tv_descriptions.setText(Html.fromHtml(infoBookView.descriptions));
        tv_name.setText(nameBook);
        tv_status.setText(infoBookView.status);
        tv_source.setText(infoBookView.source);
        Glide.with(this).load(infoBookView.pic_url).into(img_thumb);

    }

    @Override
    public void onLoadInfoFailed(String message) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
