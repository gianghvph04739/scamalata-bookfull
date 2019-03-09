package com.skyreds.truyenfull.ui.activity.viewbook.model;

public class InfoBookView {
    public String author;
    public String category;
    public String source;
    public String status;
    public String pic_url;
    public String descriptions;

    public InfoBookView(String author, String category, String source, String status, String pic_url, String descriptions) {
        this.author = author;
        this.category = category;
        this.source = source;
        this.status = status;
        this.pic_url = pic_url;
        this.descriptions = descriptions;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
