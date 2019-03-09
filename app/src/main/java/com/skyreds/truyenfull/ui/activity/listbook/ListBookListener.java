package com.skyreds.truyenfull.ui.activity.listbook;

import com.skyreds.truyenfull.ui.fragment.feature.model.HotBook;

import java.util.ArrayList;

public interface ListBookListener {
    void onLoadCategorySucessful(ArrayList<HotBook> lst);
    void onLoadCategoryFailed(String message);

    void onLoadMoreCategorySucessful(ArrayList<HotBook> lst);
    void onLoadMoreCategoryFailed(String message);
}
