package com.skyreds.truyenfull.view.activity.viewbook;

import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.ArrayList;

public interface ViewBookListener {
    void onLoadInfoSucessful(ArrayList<HotBook> lst);
    void onLoadInfoFailed(String message);
}
