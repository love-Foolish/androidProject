package com.app.qiuhutu.cookman.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.qiuhutu.cookman.R;
import com.app.qiuhutu.cookman.db.MenuChildsCategory;

import java.util.List;

public class ChannelEditAdapter extends RecyclerView.Adapter<ChannelEditAdapter.ViewHolder> {

    private List<String> channelNames;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView channel;

        public ViewHolder(View itemView) {
            super(itemView);
            channel = (TextView) itemView.findViewById(R.id.channel_category_name);
        }
    }

    public ChannelEditAdapter(List<String> channelNames) {
        this.channelNames = channelNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
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
