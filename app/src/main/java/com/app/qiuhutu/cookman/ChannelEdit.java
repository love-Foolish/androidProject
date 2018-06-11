package com.app.qiuhutu.cookman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.app.qiuhutu.cookman.adapters.ChannelEditAdapter;
import com.app.qiuhutu.cookman.adapters.ChannelRecyclerVIewAdapter;
import com.app.qiuhutu.cookman.db.MenuChildsCategory;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class ChannelEdit extends AppCompatActivity {

    private List<String> channelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_edit);

        setChannel();

    }

    private void setChannel() {
        initChannel();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.channel_category_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);
        ChannelEditAdapter adapter = new ChannelEditAdapter(channelList);
        recyclerView.setAdapter(adapter);
    }

    private void initChannel() {

        List<MenuChildsCategory> menuChildsCategoryList;

        menuChildsCategoryList = DataSupport.findAll(MenuChildsCategory.class);
        if(menuChildsCategoryList.size() > 0){
            channelList.clear();
            for (MenuChildsCategory menuChildsCategory: menuChildsCategoryList){
                channelList.add(menuChildsCategory.getName());
            }
        }

    }
}
