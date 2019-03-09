package com.skyreds.truyenfull.ui.activity.readbook.model;

public class Chapter {
    public String chapter;
    public String url;

    public Chapter(String chapter, String url) {
        this.chapter = chapter;
        this.url = url;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
