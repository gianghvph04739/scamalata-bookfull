package com.skyreds.truyenfull.ui.activity.readbook;

import com.skyreds.truyenfull.ui.activity.readbook.model.Chapter;

import java.util.ArrayList;

public interface ReadbookListener {
    void onLoadListChapterSuccess(ArrayList<Chapter> listChapter);
    void onLoadListChapterFailed(String message);
}
