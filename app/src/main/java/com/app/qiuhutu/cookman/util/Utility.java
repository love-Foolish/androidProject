package com.app.qiuhutu.cookman.util;

import android.text.TextUtils;
import android.util.Log;

import com.app.qiuhutu.cookman.db.MenuCategory;
import com.app.qiuhutu.cookman.db.MenuChildsCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    
    private static final String TAG = "Utility";
    /**
     * 解析菜谱分类数据
     */
    public static boolean handleMenuCategoryResponse(String jsonData){
        Log.i(TAG, "这是Utility");
        try {
            JSONObject object = new JSONObject(jsonData);
            JSONArray result = object.getJSONObject("result").getJSONArray("childs");
            Log.i(TAG, result.toString());
            for(int i=0; i<result.length(); i++){

                JSONObject jsonObject = result.getJSONObject(i);

                //categoryInfo
                JSONObject categoryInfo = jsonObject.getJSONObject("categoryInfo");
                MenuCategory menuCategory = new MenuCategory();
                menuCategory.setCtgId(categoryInfo.getString("ctgId"));
                menuCategory.setName(categoryInfo.getString("name"));
                menuCategory.setParentId(categoryInfo.getString("parentId"));


                //childs
                JSONArray childs = jsonObject.getJSONArray("childs");

                for(int j=0; j<childs.length(); j++){
                    JSONObject childCategoreyInfo = childs.getJSONObject(j).getJSONObject("categoryInfo");
                    MenuChildsCategory menuChildsCategory = new MenuChildsCategory();
                    Log.i("CategoryInfo: ", childCategoreyInfo.toString());
                    menuChildsCategory.setCtgId(childCategoreyInfo.getString("ctgId"));
                    menuChildsCategory.setCtgId(childCategoreyInfo.getString("name"));
                    menuChildsCategory.setCtgId(childCategoreyInfo.getString("parentId"));

                    Log.i("CategoryInfo: ", childCategoreyInfo.getString("ctgId"));
                    Log.i("CategoryInfo: ", childCategoreyInfo.getString("name"));
                    Log.i("CategoryInfo: ", childCategoreyInfo.getString("parentId"));
                }
                menuCategory.save();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
