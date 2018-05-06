package com.app.qiuhutu.cookman.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.qiuhutu.cookman.R;

import java.util.List;

public class ChannelRecyclerVIewAdapter extends RecyclerView.Adapter<ChannelRecyclerVIewAdapter.ViewHolder> {

    private List<String> channelNames;

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView channel;

        public ViewHolder(View itemView) {
            super(itemView);
            channel = (TextView) itemView.findViewById(R.id.channel_name);
        }
    }

    public ChannelRecyclerVIewAdapter(List<String> channelName) {
        this.channelNames = channelName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String channelName = channelNames.get(position);
        holder.channel.setText(channelName);

    }

    @Override
    public int getItemCount() {
        return channelNames.size();
    }

}
