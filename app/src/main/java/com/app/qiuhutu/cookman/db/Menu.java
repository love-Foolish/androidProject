package com.app.qiuhutu.cookman.db;

import org.litepal.crud.DataSupport;

public class Menu extends DataSupport{

    private String name;
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
