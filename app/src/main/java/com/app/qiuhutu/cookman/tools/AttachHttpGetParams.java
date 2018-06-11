package com.app.qiuhutu.cookman.tools;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class AttachHttpGetParams {

    public static String attachHttpGetParams(String url, LinkedHashMap<String, String> params){

        Iterator<String> keys = params.keySet().iterator();
        Iterator<String> values = params.values().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?");

        for (int i=0;i<params.size();i++ ) {
            stringBuffer.append(keys.next()+"="+values.next());
            if (i!=params.size()-1) {
                stringBuffer.append("&");
            }
        }

        return url + stringBuffer.toString();
    }

}
