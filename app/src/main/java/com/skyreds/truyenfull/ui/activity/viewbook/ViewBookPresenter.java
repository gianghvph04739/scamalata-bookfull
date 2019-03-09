package com.skyreds.truyenfull.ui.activity.viewbook;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skyreds.truyenfull.R;
import com.skyreds.truyenfull.networking.VolleySingleton;
import com.skyreds.truyenfull.ui.activity.viewbook.model.InfoBookView;
import com.skyreds.truyenfull.ui.fragment.feature.model.HotBook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ViewBookPresenter {
    private Context context;
    private ViewBookListener listener;

    public ViewBookPresenter(Context mcontext, ViewBookListener mlisListener) {
        this.listener = mlisListener;
        this.context = mcontext;
    }

    public void loadInfoBook(String urlBook) {
        final ArrayList<HotBook> lstHotBook = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlBook, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements root = document.select("div.col-xs-12.col-info-desc");
                Elements info = root.select("div.col-xs-12.col-sm-4.col-md-4.info-holder");
                Elements desc = root.select("div.desc-text.desc-text-full");
                Elements desc2 = root.select("div.desc-text");

                String description;
                if (desc.html().toString().length()<10) {
                    description = desc2.html();
                } else {
                    description = desc.html();
                }


                Element elm_pic = info.get(0).getElementsByTag("img").first();
                String picture = elm_pic.attr("src");

                Elements elm_info = info.select("div.info");
                String author = context.getString(R.string.author) + ": " + info.select("a").first().text();

                Elements div = elm_info.select("div").next();
                Elements elm_category = div.select("a").next();

                String category = context.getString(R.string.category) + ": "+elm_category.text();

                String source = "Đang cập nhật";
                try {
                    source = div.get(1).text();
                }catch (Exception e){}

                String status= "Đang cập nhật";
                try {
                    status = div.get(2).text();
                } catch (Exception e){}

                InfoBookView infoBookView = new InfoBookView(author, category, source, status, picture, description);
                listener.onLoadInfoSucessful(infoBookView);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRor:", error.toString());
                listener.onLoadInfoFailed("Lỗi khi tải dữ liệu!");
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }

}
