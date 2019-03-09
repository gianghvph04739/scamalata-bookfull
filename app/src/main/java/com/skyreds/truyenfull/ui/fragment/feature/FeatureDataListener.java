package com.skyreds.truyenfull.ui.fragment.feature;

import com.skyreds.truyenfull.model.FeatureSlide;
import com.skyreds.truyenfull.ui.fragment.feature.model.HotBook;

import java.util.ArrayList;

public interface FeatureDataListener {
    void onHotBookSuccess(ArrayList<HotBook> lst, ArrayList<String> lstBanner);
    void onHotBookFailed(String message);

    void onLoadBannerSuccess(ArrayList<FeatureSlide> lstFeatureSlide, ArrayList<String> lstPicture);
    void onLoadBannerFailed(String message);

    void onLoadNgonTinhSuccess(ArrayList<HotBook> lst);
    void onLoadNgonTinhFailed(String message);

    void onLoadNgonTinh2Success(ArrayList<HotBook> lst);
    void onLoadNgonTinh2Failed(String message);
}
