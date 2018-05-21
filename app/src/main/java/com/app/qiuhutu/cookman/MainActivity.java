package com.app.qiuhutu.cookman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.qiuhutu.cookman.Adapters.ChannelRecyclerVIewAdapter;
import com.app.qiuhutu.cookman.Adapters.MenuAdapter;
import com.app.qiuhutu.cookman.util.Utility;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener{

    private List<String> channelList = new ArrayList<>();
    private List<Menu> menuList  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用chrome查看数据库
        Stetho.initializeWithDefaults(this);
        Fresco.initialize(this);//初始化Fresco
        setContentView(R.layout.activity_main);

        setChannel();
        setItemList();
        getMenuCategory();

    }

    private void getMenuCategory() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().
                            url("http://apicloud.mob.com/v1/cook/category/query?key=520520test").
                            build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Utility.handleMenuCategoryResponse(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void setItemList(){
        initItemList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_item_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        MenuAdapter adapter = new MenuAdapter(menuList, (MenuAdapter.OnItemClickListener) this);
        recyclerView.setAdapter(adapter);
    }

    private void initItemList() {
        Menu menu1 = new Menu("小龙虾",R.drawable.list_image1);
        menuList.add(menu1);
        Menu menu2 = new Menu("水饺", R.drawable.list_image2);
        menuList.add(menu2);
        Menu menu3 = new Menu("北京烤鸭",R.drawable.list_image3);
        menuList.add(menu3);
        Menu menu4 = new Menu("红烧肉", R.drawable.list_image4);
        menuList.add(menu4);
    }


    private void setChannel() {
        initChannel();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.channel_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        ChannelRecyclerVIewAdapter adapter = new ChannelRecyclerVIewAdapter(channelList);
        recyclerView.setAdapter(adapter);
    }

    private void initChannel() {
        channelList.add("小吃");
        channelList.add("荤菜");
        channelList.add("素菜");
        channelList.add("糖粥");
        channelList.add("西点");
        channelList.add("主食");
        channelList.add("饮品");
        channelList.add("便当");
        channelList.add("红烧");
    }



    @Override
    public void onItemClick(View view, int position) {
        //TODO 点击单项跳转到详情页
        //Toast.makeText(MainActivity.this," 测试"+menuList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, CookDetailActivity.class);
        startActivity(intent);
    }
}
