package com.app.qiuhutu.cookman.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.qiuhutu.cookman.Menu;
import com.app.qiuhutu.cookman.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<Menu> menus;

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView menuImage;
        TextView menuText;

        public ViewHolder(View itemView) {
            super(itemView);
            menuImage = (ImageView) itemView.findViewById(R.id.listImage);
            menuText = ()
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
