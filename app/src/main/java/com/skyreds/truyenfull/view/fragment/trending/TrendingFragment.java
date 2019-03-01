package com.skyreds.truyenfull.view.fragment.trending;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.library.banner.BannerLayout;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.adapter.AdapterBookStyle1;
import com.skyreds.truyenfull.adapter.AdapterBookStyle2;
import com.skyreds.truyenfull.base.BaseFragment;
import com.skyreds.truyenfull.model.FeatureSlide;
import com.skyreds.truyenfull.view.activity.listbook.ListBookActivity;
import com.skyreds.truyenfull.view.fragment.feature.FeaturePresenter;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.skyreds.truyenfull.networking.ApiEndpoint.URL_TRUYENDAMMYHAY;
import static com.skyreds.truyenfull.networking.ApiEndpoint.URL_TRUYENFULL;
import static com.skyreds.truyenfull.networking.ApiEndpoint.URL_TRUYENMOI;
import static com.skyreds.truyenfull.networking.ApiEndpoint.URL_TRUYENNGONTINHHAY;

public class TrendingFragment extends BaseFragment implements TrendingDataListener {

    private OnFragmentInteractionListener mListener;
    private TrendingPresenter trendingPresenter;
    private ArrayList<HotBook> lstNew;
    private ArrayList<HotBook> lstDamMy;
    private ArrayList<HotBook> lstFull;
    @BindView(R.id.rv_new)
    RecyclerView rvNew;

    @BindView(R.id.rv_dammy)
    RecyclerView rvDamMy;

    @BindView(R.id.rv_full)
    RecyclerView rvFull;

    @BindView(R.id.seeall_dammyhot)
    TextView seeall_dammyhot;
    @BindView(R.id.seeall_truyenhoanthanh)
    TextView seeall_truyenhoanthanh;
    @BindView(R.id.seeall_truyenmoi)
    TextView seeall_truyenmoi;


    private LinearLayoutManager manager;
    Unbinder unbinder;
    private AdapterBookStyle1 adapterBookStyle1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUnBinder(unbinder, view);
        trendingPresenter = new TrendingPresenter(getContext(), this);
        setUp(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void setUp(View view) {
        trendingPresenter.loadDamMy();
        trendingPresenter.loadFull();
        trendingPresenter.loadNew();
    }

    @OnClick(R.id.seeall_truyenmoi)
    void onClickNgonTinhHay() {
        Intent i = new Intent(getContext(), ListBookActivity.class);
        i.putExtra("url", URL_TRUYENMOI);
        startActivity(i);
    }

    @OnClick(R.id.seeall_dammyhot)
    void onClickDamMyHot() {
        Intent i = new Intent(getContext(), ListBookActivity.class);
        i.putExtra("url", URL_TRUYENDAMMYHAY);
        startActivity(i);
    }

    @OnClick(R.id.seeall_truyenhoanthanh)
    void onClickTruyenHoanThanh() {
        Intent i = new Intent(getContext(), ListBookActivity.class);
        i.putExtra("url", URL_TRUYENFULL);
        startActivity(i);
    }

    @Override
    public void onNewSuccess(ArrayList<HotBook> lst) {
        lstNew = lst;
        adapterBookStyle1 = new AdapterBookStyle1(getContext(), lstNew);
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvNew.setLayoutManager(manager);
        rvNew.setHasFixedSize(true);
        rvNew.setItemAnimator(new DefaultItemAnimator());
        rvNew.setAdapter(adapterBookStyle1);
    }

    @Override
    public void onNewFailed(String message) {

    }

    @Override
    public void onDamMySuccess(ArrayList<HotBook> lst) {
        lstDamMy = lst;
        adapterBookStyle1 = new AdapterBookStyle1(getContext(), lstDamMy);
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDamMy.setLayoutManager(manager);
        rvDamMy.setHasFixedSize(true);
        rvDamMy.setItemAnimator(new DefaultItemAnimator());
        rvDamMy.setAdapter(adapterBookStyle1);
    }

    @Override
    public void onDamMyFailed(String message) {

    }

    @Override
    public void onFullSuccess(ArrayList<HotBook> lst) {
        lstFull = lst;
        adapterBookStyle1 = new AdapterBookStyle1(getContext(), lstFull);
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvFull.setLayoutManager(manager);
        rvFull.setHasFixedSize(true);
        rvFull.setItemAnimator(new DefaultItemAnimator());
        rvFull.setAdapter(adapterBookStyle1);
    }

    @Override
    public void onFullFailed(String message) {
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
