package com.app.qiuhutu.cookman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.app.qiuhutu.cookman.adapters.ChannelRecyclerVIewAdapter;
import com.app.qiuhutu.cookman.adapters.MenuAdapter;
import com.app.qiuhutu.cookman.db.Menu;
import com.app.qiuhutu.cookman.db.MenuChildsCategory;
import com.app.qiuhutu.cookman.util.Utility;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.app.qiuhutu.cookman.tools.AttachHttpGetParams.attachHttpGetParams;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener{

    private List<String> channelList = new ArrayList<>();
    private List<Menu> menuList  = new ArrayList<>();
    private String curCtgId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用chrome查看数据库
        Stetho.initializeWithDefaults(this);
        Fresco.initialize(this);//初始化Fresco
        setContentView(R.layout.activity_main);

        getMenuCategory();
        getMenuTag();
        setChannel();
        setItemList();

        ImageButton spreadBtn = (ImageButton) findViewById(R.id.btn_channelEdit);
        spreadBtn.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChannelEdit.class);
                startActivity(intent);
            }
        });

    }

    private void getMenuCategory() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().
                            url("http://apicloud.mob.com/v1/cook/category/query?key=260d173130050").
                            build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Utility.handleMenuCategoryResponse(responseData);
                } catch (IOException e) {
                    Log.i("weberror", "连接网络失败");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getMenuTag() {

        new Thread(new Runnable() {

            @Override
            public void run() {

                LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("page", "1");
                hashMap.put("name", "%E7%BA%A2%E7%83%A7%E8%82%89");
                hashMap.put("cid", "0010001007");
                hashMap.put("key", "260d173130050");
                hashMap.put("size", "20");
                Log.i("attachHttpGetParams", attachHttpGetParams("http://apicloud.mob.com/v1/cook/menu/search", hashMap));

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().
                            url(attachHttpGetParams("http://apicloud.mob.com/v1/cook/menu/search", hashMap)).
                            build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Utility.handleMenuByTagResponse(responseData);
                } catch (IOException e) {
                    Log.i("weberror", "连接网络失败");
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
        MenuAdapter adapter = new MenuAdapter(menuList,MainActivity.this);
        recyclerView.setAdapter(adapter);
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

    private void initItemList() {
        List<Menu> menus;

        menus = DataSupport.findAll(Menu.class);
        if(menus.size() > 0){
            menuList.clear();
            for(int i=0 ; i< 20; i++){
                Menu menu = menus.get(i);
                menuList.add(menu);
            }
        }
    }

    private void initChannel() {

        List<MenuChildsCategory> menuChildsCategoryList;

        menuChildsCategoryList = DataSupport.findAll(MenuChildsCategory.class);
        if(menuChildsCategoryList.size() > 0){
            channelList.clear();
            for (int i = 0 ; i < 8; i++){
                MenuChildsCategory menuChildsCategory = menuChildsCategoryList.get(i);
                channelList.add(menuChildsCategory.getName());
            }
        }

    }



    @Override
    public void onItemclick(Menu menu) {
        Log.i("======","可以点击吗？");
        Toast.makeText(MainActivity.this,menu.getName(),Toast.LENGTH_SHORT).show();
    }
}
