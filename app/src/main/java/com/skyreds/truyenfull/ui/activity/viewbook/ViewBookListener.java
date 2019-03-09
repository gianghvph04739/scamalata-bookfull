package com.skyreds.truyenfull.ui.activity.viewbook;

import com.skyreds.truyenfull.ui.activity.viewbook.model.InfoBookView;

public interface ViewBookListener {
    void onLoadInfoSucessful(InfoBookView infoBookView);
    void onLoadInfoFailed(String message);
}
