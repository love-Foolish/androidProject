package com.app.qiuhutu.cookman.Adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.qiuhutu.cookman.MainActivity;
import com.app.qiuhutu.cookman.Menu;
import com.app.qiuhutu.cookman.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> implements View.OnClickListener{
    private List<Menu> menus;


    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View menuView;
        ImageView menuImage;
        TextView menuText;

        public ViewHolder(View itemView) {
            super(itemView);
            menuView = itemView;
            menuImage = (ImageView) itemView.findViewById(R.id.listImage);
            menuText = (TextView) itemView.findViewById(R.id.lisTitle);

        }
    }

    public MenuAdapter(List<Menu> menus,OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        this.menus = menus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cook_list_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Menu menu = menus.get(position);
                Toast.makeText(view.getContext(), "you clicked view "+ menu.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu menu = menus.get(position);
        holder.menuImage.setImageResource(menu.getImgId());
        holder.menuText.setText(menu.getTitle());
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(view,(int) view.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }


}
