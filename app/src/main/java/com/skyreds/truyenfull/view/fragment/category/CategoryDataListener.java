package com.skyreds.truyenfull.view.fragment.category;

import com.skyreds.truyenfull.model.FeatureSlide;
import com.skyreds.truyenfull.view.fragment.category.model.Category;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.ArrayList;

public interface CategoryDataListener {
    void onLoadCategorySucessful(ArrayList<Category> lst);
    void onLoadCategoryFailed(String message);
}
