package com.app.qiuhutu.cookman.db;

import org.litepal.crud.DataSupport;

public class MenuCategory extends DataSupport{

    private String ctgId;

    private String name;

    private String parentId;


    public String getCtgId() {
        return ctgId;
    }

    public void setCtgId(String ctgId) {
        this.ctgId = ctgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
