package com.skyreds.truyenfull.view.activity.listbook;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skyreds.truyenfull.networking.VolleySingleton;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ListBookPresenter {
    private Context context;
    private ListBookListener listener;

    public ListBookPresenter(Context mcontext, ListBookListener mlisListener) {
        this.listener = mlisListener;
        this.context = mcontext;
    }

    public void loadCategory(String urlCategory) {
        final ArrayList<HotBook> lstHotBook = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlCategory, new Response.Listener<String>() {
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
                    lstHotBook.add(hotBook);
                    i++;
                }
                listener.onLoadCategorySucessful(lstHotBook);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRor:", error.toString());
                listener.onLoadCategoryFailed("Lỗi khi tải dữ liệu!");
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }

    public void loadMoreCategory(String urlCategory) {
        final ArrayList<HotBook> lstHotBook = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlCategory, new Response.Listener<String>() {
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
                    lstHotBook.add(hotBook);
                    i++;
                }
                listener.onLoadMoreCategorySucessful(lstHotBook);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRor:", error.toString());
                listener.onLoadMoreCategoryFailed("Lỗi khi tải dữ liệu!");
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }
}
