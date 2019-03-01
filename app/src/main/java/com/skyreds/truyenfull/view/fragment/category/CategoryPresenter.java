package com.skyreds.truyenfull.view.fragment.category;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skyreds.truyenfull.model.FeatureSlide;
import com.skyreds.truyenfull.networking.VolleySingleton;
import com.skyreds.truyenfull.view.fragment.category.model.Category;
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

public class CategoryPresenter {
    private Context context;
    private CategoryDataListener listener;

    public CategoryPresenter(Context mcontext, CategoryDataListener mlisListener) {
        this.listener = mlisListener;
        this.context = mcontext;
    }

    public void loadCategory() {
        final ArrayList<Category> lstCategory = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements elements1 = document.select("div.dropdown-menu.multi-column");
                Elements element = elements1.select("li").next();
                int i = 1;
                lstCategory.clear();
                for (Element elm : element) {
                    Element elm_cate = elm.getElementsByTag("a").first();
                    String name = elm_cate.text();
                    String url = elm_cate.attr("href");
                    lstCategory.add(new Category(name,url));
                    i++;
                }
                listener.onLoadCategorySucessful(lstCategory);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onLoadCategoryFailed("Lỗi khi tải dữ liệu!");
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).getRequestQueue().add(stringRequest);
    }
}
