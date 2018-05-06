package com.app.qiuhutu.cookman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.qiuhutu.cookman.Adapters.ChannelRecyclerVIewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> channelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initChannel();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.channel_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        ChannelRecyclerVIewAdapter adapter = new ChannelRecyclerVIewAdapter(channelList);
        recyclerView.setAdapter(adapter);

    }

    private void initChannel() {
        for(int i=0; i<2; i++){
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
    }
}
