package com.skyreds.truyenfull.ui.fragment.feature.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HotBook implements Parcelable {

    public String name;
    public String author;
    public String chapter;
    public String pic_landcape;
    public String pic_portairt;
    public String link_book;

    public HotBook(String name, String author, String chapter, String pic_landcape, String pic_portairt, String link_book) {
        this.name = name;
        this.author = author;
        this.chapter = chapter;
        this.pic_landcape = pic_landcape;
        this.pic_portairt = pic_portairt;
        this.link_book = link_book;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getPic_landcape() {
        return pic_landcape;
    }

    public void setPic_landcape(String pic_landcape) {
        this.pic_landcape = pic_landcape;
    }

    public String getPic_portairt() {
        return pic_portairt;
    }

    public void setPic_portairt(String pic_portairt) {
        this.pic_portairt = pic_portairt;
    }

    public String getLink_book() {
        return link_book;
    }

    public void setLink_book(String link_book) {
        this.link_book = link_book;
    }

    public static Creator<HotBook> getCREATOR() {
        return CREATOR;
    }

    public String getName() {
        return name;
    }

    protected HotBook(Parcel in) {

    }

    public static final Creator<HotBook> CREATOR = new Creator<HotBook>() {
        @Override
        public HotBook createFromParcel(Parcel in) {
            return new HotBook(in);
        }

        @Override
        public HotBook[] newArray(int size) {
            return new HotBook[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
