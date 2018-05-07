package com.app.qiuhutu.cookman;

public class Menu {

    private String title;
    private int imgId;

    public Menu(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public int getImgId() {
        return imgId;
    }
}
