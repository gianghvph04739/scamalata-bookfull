package com.skyreds.truyenfull.view.fragment.feature;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.library.banner.BannerLayout;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.adapter.WebBannerAdapter;
import com.skyreds.truyenfull.base.BaseFragment;
import com.skyreds.truyenfull.model.FeatureSlide;
import com.skyreds.truyenfull.networking.VolleySingleton;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;
import com.skyreds.truyenfull.view.main.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.skyreds.truyenfull.networking.ApiEndpoint.BASE_URL;

public class FeatureFragment extends BaseFragment implements FeatureDataListener {

    private OnFragmentInteractionListener mListener;
    private FeaturePresenter featurePresenter;
    private ArrayList<FeatureSlide> lstBanner;
    private List<String> lstPicture;
    @BindView(R.id.recycler)
    BannerLayout recyclerBanner;
    Unbinder unbinder;

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
    }


    @Override
    public void onHotBookSuccess(ArrayList<HotBook> lst, ArrayList<String> lstBanner) {
        lstPicture = lstBanner;
        setUpBanner();
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
