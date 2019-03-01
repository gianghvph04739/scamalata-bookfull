package com.skyreds.truyenfull.view.fragment.feature;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.banner.BannerLayout;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.adapter.AdapterBookStyle1;
import com.skyreds.truyenfull.adapter.AdapterBookStyle2;
import com.skyreds.truyenfull.adapter.WebBannerAdapter;
import com.skyreds.truyenfull.base.BaseFragment;
import com.skyreds.truyenfull.model.FeatureSlide;
import com.skyreds.truyenfull.view.activity.listbook.ListBookActivity;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.skyreds.truyenfull.networking.ApiEndpoint.URL_TRUYENHOT;
import static com.skyreds.truyenfull.networking.ApiEndpoint.URL_TRUYENNGONTINHHAY;

public class FeatureFragment extends BaseFragment implements FeatureDataListener {

    private OnFragmentInteractionListener mListener;
    private FeaturePresenter featurePresenter;
    private ArrayList<FeatureSlide> lstBanner;
    private ArrayList<HotBook> lstHotbook;
    private ArrayList<HotBook> lstNgonTinhHot;
    private ArrayList<HotBook> lstNgonTinhHot2;
    private List<String> lstPicture;
    @BindView(R.id.recycler)
    BannerLayout recyclerBanner;

    @BindView(R.id.rv_Recommend)
    RecyclerView rvRecommend;

    @BindView(R.id.rv_ngontinhhot)
    RecyclerView rvNgonTinhHot;

    @BindView(R.id.rv_ngontinhhot2)
    RecyclerView rvNgonTinh2Hot;
    @BindView(R.id.seeall_goiy)
    TextView seeall_goiy;
    @BindView(R.id.seeall_ngontinhhay)
    TextView seeall_ngontinhhay;


    private LinearLayoutManager manager;
    Unbinder unbinder;
    private  AdapterBookStyle1 adapterBookStyle1;
    private  AdapterBookStyle2 adapterBookStyle2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feature, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUnBinder(unbinder, view);
        featurePresenter = new FeaturePresenter(getContext(), this);
        setUp(view);
        return view;
    }

    private void setUpBanner() {
        WebBannerAdapter webBannerAdapter = new WebBannerAdapter(getContext(), lstPicture);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Image " + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerBanner.setAdapter(webBannerAdapter);
    }

    @OnClick(R.id.seeall_goiy)
    void onClickGoiY(){
        Intent i = new Intent(getContext(), ListBookActivity.class);
        i.putExtra("url",URL_TRUYENHOT);
        startActivity(i);
    }

    @OnClick(R.id.seeall_ngontinhhay)
    void onClickNgonTinhHay(){
        Intent i = new Intent(getContext(), ListBookActivity.class);
        i.putExtra("url",URL_TRUYENNGONTINHHAY);
        startActivity(i);
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
        featurePresenter.loadBookHot();
        featurePresenter.loadBanner();
        featurePresenter.loadNgonTinhHot();
        featurePresenter.loadNgonTinh2();

    }


    @Override
    public void onHotBookSuccess(ArrayList<HotBook> lst, ArrayList<String> lstBanner) {
        lstPicture = lstBanner;
        lstHotbook  = lst;
        setUpBanner();
        loadHotbook();

    }

    private void loadHotbook(){
        adapterBookStyle1 = new AdapterBookStyle1(getContext(),lstHotbook);
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rvRecommend.setLayoutManager(manager);
        rvRecommend.setHasFixedSize(true);
        rvRecommend.setItemAnimator(new DefaultItemAnimator());
        rvRecommend.setAdapter(adapterBookStyle1);
    }

    @Override
    public void onHotBookFailed(String message) {
        showMessage(message);
    }

    @Override
    public void onLoadBannerSuccess(ArrayList<FeatureSlide> lstFeatureSlide, ArrayList<String> lstPictures) {
    }

    @Override
        public void onLoadBannerFailed(String message) {

    }

    @Override
    public void onLoadNgonTinhSuccess(ArrayList<HotBook> lst) {
        lstNgonTinhHot= lst;
        adapterBookStyle2 = new AdapterBookStyle2(getContext(),lstNgonTinhHot);
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rvNgonTinhHot.setLayoutManager(manager);
        rvNgonTinhHot.setHasFixedSize(true);
        rvNgonTinhHot.setItemAnimator(new DefaultItemAnimator());
        rvNgonTinhHot.setAdapter(adapterBookStyle2);
    }

    @Override
    public void onLoadNgonTinhFailed(String message) {
        Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadNgonTinh2Success(ArrayList<HotBook> lst) {
        lstNgonTinhHot2= lst;
        adapterBookStyle2 = new AdapterBookStyle2(getContext(),lstNgonTinhHot2);
        manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        rvNgonTinh2Hot.setLayoutManager(manager);
        rvNgonTinh2Hot.setHasFixedSize(true);
        rvNgonTinh2Hot.setItemAnimator(new DefaultItemAnimator());
        rvNgonTinh2Hot.setAdapter(adapterBookStyle2);
    }

    @Override
    public void onLoadNgonTinh2Failed(String message) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
