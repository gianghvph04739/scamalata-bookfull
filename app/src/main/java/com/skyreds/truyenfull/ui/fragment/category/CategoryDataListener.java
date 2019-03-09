package com.skyreds.truyenfull.ui.fragment.category;

import com.skyreds.truyenfull.ui.fragment.category.model.Category;

import java.util.ArrayList;

public interface CategoryDataListener {
    void onLoadCategorySucessful(ArrayList<Category> lst);
    void onLoadCategoryFailed(String message);
}
