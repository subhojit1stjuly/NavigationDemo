package com.reprototyper.navigationdemo.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.reprototyper.navigationdemo.MenuModels.MenuModel;
import com.reprototyper.navigationdemo.R;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MyRecyclerViewHolder> {

    Activity activity;
    private final List<MenuModel> menuList;
    // Need this for the Main-list item onClick events
    private OnItemsClickListener listener;

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);

        return new MyRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        MenuModel modelItems =menuList.get(position);
            holder.img.setImageResource(modelItems.getMenuIcon());
            holder.tvMenuName.setText(modelItems.getMenuName());
            holder.cardView_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        listener.onItemClick(modelItems);
                    }
                }
            });
            if(menuList.get(position).getSubMenuModelList().size()>0){
                holder.tvBtn.setBackground(activity.getDrawable(R.drawable.ic_arrow_right));
            }else{
                holder.tvBtn.setBackground(null);
            }
//            holder.cardView_menu.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.left_to_right));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }


    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvMenuName,tvBtn;
        CardView cardView_menu;

        public MyRecyclerViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tvMenuName = view.findViewById(R.id.tvMenuName);
            tvBtn = view.findViewById(R.id.tvBtn);
            cardView_menu = view.findViewById(R.id.cardView_menu);
        }
    }

    public MainMenuAdapter(List<MenuModel> re_dataList, Activity act) {
        this.menuList = re_dataList;
        activity = act;
    }

    // Interface to perform events on Main-List item click
    public interface OnItemsClickListener{
        void onItemClick(MenuModel menuModel);
    }
    // Main-list item clickListener is initialized
// This will be used in MainActivity
    public void setWhenClickListener(OnItemsClickListener listener){
        this.listener = listener;
    }
}
