package com.skyreds.truyenfull.view.fragment.feature;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skyreds.truyenfull.model.FeatureSlide;
import com.skyreds.truyenfull.networking.VolleySingleton;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import static com.skyreds.truyenfull.networking.ApiEndpoint.BASE_URL;
import static com.skyreds.truyenfull.networking.ApiEndpoint.URL_TRUYENHOT;
import static com.skyreds.truyenfull.networking.ApiEndpoint.URL_TRUYENNGONTINHHAY;
import static com.skyreds.truyenfull.networking.ApiEndpoint.URL_TRUYENNGONTINHHAY2;

public class FeaturePresenter  {
    private Context context;
    private FeatureDataListener listener;
    public FeaturePresenter(Context mcontext,FeatureDataListener mlisListener) {
        this.listener = mlisListener;
        this.context = mcontext;
    }

    public void loadBookHot() {
        final ArrayList<HotBook> lstHotBook = new ArrayList<>();
        final ArrayList<String> lstPicture = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TRUYENHOT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements elements1 = document.select("div.col-xs-12.col-sm-12.col-md-9.col-truyen-main");
                Elements elements = elements1.select("div.list.list-truyen.col-xs-12");
                Elements element = elements.select("div.row").next();
                int i = 1;
                lstHotBook.clear();
                for (Element elm : element) {
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
                    String chapters = elm_chapter.attr("title");
                    String[] parts = chapters.split("-");
                    String chapter = parts[parts.length-1];

                    HotBook hotBook = new HotBook(name,author,chapter,pic_landcape,pic_portairt,url);
                    lstPicture.add(pic_landcape);
                    lstHotBook.add(hotBook);
                    i++;
                }
                listener.onHotBookSuccess(lstHotBook,lstPicture);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onHotBookFailed("Lỗi khi tải dữ liệu!");
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }

    public void loadNgonTinhHot() {
        final ArrayList<HotBook> lstHotBook = new ArrayList<>();
        final ArrayList<String> lstPicture = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TRUYENNGONTINHHAY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements elements1 = document.select("div.col-xs-12.col-sm-12.col-md-9.col-truyen-main");
                Elements elements = elements1.select("div.list.list-truyen.col-xs-12");
                Elements element = elements.select("div.row").next();
                int i = 1;
                lstHotBook.clear();
                for (Element elm : element) {
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
                    String chapters = elm_chapter.attr("title");
                    String[] parts = chapters.split("-");
                    String chapter = parts[parts.length-1];

                    HotBook hotBook = new HotBook(name,author,chapter,pic_landcape,pic_portairt,url);
                    lstPicture.add(pic_landcape);
                    lstHotBook.add(hotBook);
                    i++;
                }
                listener.onLoadNgonTinhSuccess(lstHotBook);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRor:", error.toString());
                listener.onLoadNgonTinhFailed("Lỗi khi tải dữ liệu!");
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }

    public void loadNgonTinh2() {
        final ArrayList<HotBook> lstHotBook = new ArrayList<>();
        final ArrayList<String> lstPicture = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TRUYENNGONTINHHAY2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements elements1 = document.select("div.col-xs-12.col-sm-12.col-md-9.col-truyen-main");
                Elements elements = elements1.select("div.list.list-truyen.col-xs-12");
                Elements element = elements.select("div.row").next();
                int i = 1;
                lstHotBook.clear();
                for (Element elm : element) {
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
                    String chapters = elm_chapter.attr("title");
                    String[] parts = chapters.split("-");
                    String chapter = parts[parts.length-1];

                    HotBook hotBook = new HotBook(name,author,chapter,pic_landcape,pic_portairt,url);
                    lstPicture.add(pic_landcape);
                    lstHotBook.add(hotBook);
                    i++;
                }
                listener.onLoadNgonTinh2Success(lstHotBook);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRor:", error.toString());
                listener.onLoadNgonTinh2Failed("Lỗi khi tải dữ liệu!");
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }


    public void loadBanner() {
        final ArrayList<FeatureSlide> lstBanner = new ArrayList<>();
        final ArrayList<String> lstPicture = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                lstBanner.clear();
                Elements elements = document.select("div.index-intro");
                Elements list = elements.select("div");
                int i = 1;
                for (Element element : list) {
                    if (element.attr("itemtype").equals("https://schema.org/Book")) {
                        Element elm_picture = element.select("img").first();
                        Element elm_url = element.getElementsByTag("a").first();
                        String picture = elm_picture.attr("src");
                        String url = elm_url.attr("href");
                        String name = element.getElementsByTag("h3").first().text();
                        lstBanner.add(new FeatureSlide(name, picture, url));
                        i++;
                    }
                }
                listener.onLoadBannerSuccess(lstBanner,lstPicture);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRor:", error.toString());
                listener.onLoadBannerFailed("Lỗi khi tải dữ liệu!");
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);

    }

}
