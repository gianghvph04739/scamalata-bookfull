package com.skyreds.truyenfull.view.fragment.trending;

import com.skyreds.truyenfull.model.FeatureSlide;
import com.skyreds.truyenfull.view.fragment.feature.model.HotBook;

import java.util.ArrayList;

public interface TrendingDataListener {
    void onNewSuccess(ArrayList<HotBook> lst);
    void onNewFailed(String message);

    void onDamMySuccess(ArrayList<HotBook> lst);
    void onDamMyFailed(String message);

    void onFullSuccess(ArrayList<HotBook> lst);
    void onFullFailed(String message);
}
