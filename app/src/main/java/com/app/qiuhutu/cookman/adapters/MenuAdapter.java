package com.app.qiuhutu.cookman.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.qiuhutu.cookman.db.Menu;
import com.app.qiuhutu.cookman.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{

    private List<Menu> menus;
    // 声明一个接口的对象
    private OnItemClickListener mOnItemClickListener ;
    // 定义一个内部的监听器接口，在接口内部定义onclick
    public interface OnItemClickListener{
        void onItemclick(Menu menu);
    }

    public MenuAdapter(List<Menu> menus,OnItemClickListener mOnItemClickListener) {
        this.menus = menus;
        this.mOnItemClickListener = mOnItemClickListener;

    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

         View menuView;
         ImageView menuImage;
         TextView menuText;

        public ViewHolder(View itemView) {
            super(itemView);
            menuView = itemView;
            menuImage = (ImageView) itemView.findViewById(R.id.listImage);
            menuText = (TextView) itemView.findViewById(R.id.lisTitle);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Menu menu = menus.get(position);
            mOnItemClickListener.onItemclick(menu);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cook_list_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Menu menu = menus.get(position);
        //holder.menuImage.setImageResource(menu.getImg());
        holder.menuText.setText(menu.getName());

        Picasso.get().load(menu.getImg()).into(holder.menuImage);
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

}
