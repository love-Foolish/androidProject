package com.app.qiuhutu.cookman.db;

import org.litepal.crud.DataSupport;

public class MenuChildsCategory extends DataSupport{

    private String ctgId;

    private String name;

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
}
