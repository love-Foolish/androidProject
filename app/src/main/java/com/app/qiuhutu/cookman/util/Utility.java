package com.app.qiuhutu.cookman.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.app.qiuhutu.cookman.db.Menu;
import com.app.qiuhutu.cookman.db.MenuCategory;
import com.app.qiuhutu.cookman.db.MenuChildsCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utility {
    
    private static final String TAG = "Utility";
    /**
     * 解析菜谱分类数据
     */
    public static boolean handleMenuCategoryResponse(String jsonData){
        if(!TextUtils.isEmpty(jsonData)){
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
                    menuCategory.save();

                    //childs
                    JSONArray childs = jsonObject.getJSONArray("childs");

                    for(int j=0; j<childs.length(); j++){
                        JSONObject childCategoryInfo = childs.getJSONObject(j).getJSONObject("categoryInfo");
                        MenuChildsCategory menuChildsCategory = new MenuChildsCategory();
                        menuChildsCategory.setCtgId(childCategoryInfo.getString("ctgId"));
                        menuChildsCategory.setName(childCategoryInfo.getString("name"));
                        menuChildsCategory.setParentId(childCategoryInfo.getString("parentId"));
                        menuChildsCategory.save();
                    }
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析按标签查询菜谱接口
     */

    public static boolean handleMenuByTagResponse(String jsonData) {
            Log.i(TAG, "这是Utility");
            try {
                JSONObject object = new JSONObject(jsonData);
                JSONArray list = object.getJSONObject("result").getJSONArray("list");
                for (int i = 0; i<list.length(); i++){
                    JSONObject jsonObject = list.getJSONObject(i);
                    Menu menu = new Menu();
                    menu.setName(jsonObject.getString("name"));
                    String imgUrl = jsonObject.getString("thumbnail");
                    if(imgUrl.startsWith("http:")){
                        imgUrl = imgUrl.replace("http://", "https://");
                    }
                    menu.setImg(imgUrl);
                    Log.i("ImageUrl", imgUrl);
                    String imgByte = null;
                    //getImgByte(imgUrl, imgByte);
                    menu.save();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return false;
    }

    public static void getImgByte(final String url, final String imgBytes) {


        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.i("getImgByte", "这是getImgByte");
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        byte[] bytes = response.body().bytes();

                        String stringByte = null;

                        /*
                        for (int i = 0; i<bytes.length; i++) {
                            stringByte += bytes[i];
                        }
                        */
                        Log.i("StringByte", stringByte);
                    }
                });

            }
        }).start();
    }

}
