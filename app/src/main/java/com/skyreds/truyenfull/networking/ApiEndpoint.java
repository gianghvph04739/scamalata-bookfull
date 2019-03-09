package com.skyreds.truyenfull.networking;

public class ApiEndpoint {
    public static final String BASE_URL = "https://truyenfull.vn/";
    public static final String URL_TRUYENHOT = BASE_URL + "danh-sach/truyen-hot/";
    public static final String URL_TRUYENNGONTINHHAY = BASE_URL + "danh-sach/ngon-tinh-hay/";
    public static final String URL_TRUYENNGONTINHHAY2 = BASE_URL + "danh-sach/ngon-tinh-sung/";
    public static final String URL_TRUYENFULL = BASE_URL + "danh-sach/truyen-full/";
    public static final String URL_TRUYENDAMMYHAY = BASE_URL + "danh-sach/dam-my-hay/";
    public static final String URL_TRUYENMOI = BASE_URL + "danh-sach/truyen-moi/";

    public static final String getEnpointPage(int page) {
        return "trang-" + page + "/#list-chapter";
    }

}
