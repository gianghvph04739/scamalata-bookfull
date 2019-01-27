package com.skyreds.truyenfull.model;

public class FeatureSlide {
    public String nameBook;
    public String picture;
    public String urlBook;

    public FeatureSlide(String nameBook, String picture, String urlBook) {
        this.nameBook = nameBook;
        this.picture = picture;
        this.urlBook = urlBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUrlBook() {
        return urlBook;
    }

    public void setUrlBook(String urlBook) {
        this.urlBook = urlBook;
    }



}
