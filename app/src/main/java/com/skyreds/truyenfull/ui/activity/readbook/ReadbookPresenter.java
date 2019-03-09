package com.skyreds.truyenfull.ui.activity.readbook;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skyreds.truyenfull.networking.VolleySingleton;
import com.skyreds.truyenfull.ui.activity.readbook.model.Chapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ReadbookPresenter {
    private Context context;
    private ReadbookListener readbookListener;

    public ReadbookPresenter(Context context, ReadbookListener readbookListener) {
        this.context = context;
        this.readbookListener = readbookListener;
    }

    public void loadInfoBook(final String urlBook) {
        final ArrayList<Chapter> lstChapter = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlBook, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                lstChapter.clear();
                Document document = Jsoup.parse(response);
                Elements root = document.select("div.col-xs-12.col-sm-12.col-md-9.col-truyen-main");
                Elements childnode = root.select("div#list-chapter");
                Elements child = childnode.select("div.row");
                Elements li = child.select("li").next();
                Element li1 = childnode.get(0).getElementsByTag("li").first();
                Elements elm_chapter = li1.getElementsByTag("a");
                String chap = elm_chapter.text();
                String u = elm_chapter.attr("href").toString();
                lstChapter.add(new Chapter(chap,u));
                Log.e("Chapter",chap);
                for(Element elm : li){
                    Elements elm_chap = elm.getElementsByTag("a");
                    String chapter = elm_chap.text();
                    String url = elm_chap.attr("href");
                    lstChapter.add(new Chapter(chapter,url));
                    Log.e("Chapter",chapter);
                }

                readbookListener.onLoadListChapterSuccess(lstChapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRor:", error.toString());
//                listener.onLoadInfoFailed("Lỗi khi tải dữ liệu!");
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }

}
