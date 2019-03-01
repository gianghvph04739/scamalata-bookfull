package com.skyreds.truyenfull.view.activity.listbook;

import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.ArrayList;

public interface ListBookListener {
    void onLoadCategorySucessful(ArrayList<HotBook> lst);
    void onLoadCategoryFailed(String message);

    void onLoadMoreCategorySucessful(ArrayList<HotBook> lst);
    void onLoadMoreCategoryFailed(String message);
}
