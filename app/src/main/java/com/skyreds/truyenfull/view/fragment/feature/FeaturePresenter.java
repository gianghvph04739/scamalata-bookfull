package com.skyreds.truyenfull.view.fragment.feature;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skyreds.truyenfull.networking.VolleySingleton;
import com.skyreds.truyenfull.view.main.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FeaturePresenter {
    private String URL_TRUYENDAHOANTHANH = "https://truyenfull.vn/danh-sach/truyen-hot/";

    private Context context;

    public FeaturePresenter(Context context) {
        this.context = context;
    }

    private void loadBookHot() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TRUYENDAHOANTHANH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements elements1 = document.select("div.col-xs-12.col-sm-12.col-md-9.col-truyen-main");
                Elements elements = elements1.select("div.list.list-truyen.col-xs-12");
                Elements element = elements.select("div.row").next();
                Log.e("size:", element.size() + "");
                int i = 1;
                for (Element elm : element) {
                    Log.e("Element:", elm.toString());
                    Element elm_pic = elm.select("div.lazyimg").first();
                    String pic_portairt = elm_pic.attr("data-image");
                    String pic_landcape = elm_pic.attr("data-desk-image");

                    Element div_title = elm.select("div.col-xs-7").first();
                    String name = div_title.getElementsByTag("h3").text();
                    Element span_author = div_title.select("span.author").first();
                    String author = span_author.text();
                    Element elm_url = div_title.getElementsByTag("a").first();
                    String url = elm_url.attr("href");

                    Element div_chapter = elm.select("div.col-xs-2.text-info").first();
                    Element elm_chapter = div_chapter.getElementsByTag("a").first();
                    String chapter = elm_chapter.attr("title");

                    Log.e("ELEM " + i, name
                            + "\nchapter:" + chapter
                            + "\nauthor:" + author
                            + "\npic_landcape: " + pic_landcape
                            + "\npic_portairt" + pic_portairt
                            + "\nurl: " + url);
                    i++;
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRor:", error.toString());
                Toast.makeText(context, "Lỗi khi tải dữ liệu!", Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);

    }
}
