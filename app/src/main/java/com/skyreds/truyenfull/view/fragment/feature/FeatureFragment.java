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

public class FeatureFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

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
//        ButterKnife.bind(getActivity());
        unbinder = ButterKnife.bind(this,view);
        setUnBinder(unbinder,view);
        setUp(view);
        return view;
    }

    private void setUpBanner(){
        WebBannerAdapter webBannerAdapter=new WebBannerAdapter(getContext(),lstPicture);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Image " + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerBanner.setAdapter(webBannerAdapter);
    }
    private void loadBanner(final String url) {
        showLoading();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                lstBanner.clear();
                Elements elements = document.select("div.index-intro");
                Elements list = elements.select("div");
                try {
                    int i = 1;
                    for (Element element : list) {
                        if (element.attr("itemtype").equals("https://schema.org/Book")) {
                            Element elm_picture = element.select("img").first();
                            Element elm_url = element.getElementsByTag("a").first();
                            String picture = elm_picture.attr("src");
                            String url = elm_url.attr("href");
                            String name = element.getElementsByTag("h3").first().text();
                            lstBanner.add(new FeatureSlide(name,picture,url));
                            lstPicture.add(picture);
                            Log.e("Pic", picture);
                            i++;
                        }
                    }

//                    }
                    hideLoading();
                } catch (Exception ex) {
                }
//                lstSong.addAll(lstSongMore);
//                adapter.notifyDataSetChanged();
                hideLoading();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRor:", error.toString());
                hideLoading();
                Toast.makeText(getContext(), "Lỗi khi tải dữ liệu!", Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getContext()).getRequestQueue().add(stringRequest);

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
        lstBanner = new ArrayList<>();
        lstPicture = new ArrayList<>();
        loadBanner(BASE_URL);
        setUpBanner();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
