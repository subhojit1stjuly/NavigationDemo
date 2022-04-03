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

import com.reprototyper.navigationdemo.MenuModels.SubMenuModel;
import com.reprototyper.navigationdemo.R;

import java.util.List;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.MyRecyclerViewHolder> {

    Activity activity;
    private List<SubMenuModel> menuList;

    // Need this for the Main-list item onClick events
    private OnItemsClickListener listener;

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.submenu_item, parent, false);

        return new MyRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        SubMenuModel modelItems = menuList.get(position);
            holder.tvSubMenu.setText(modelItems.getSubMenuName());
            holder.imgSubMenu.setImageResource(modelItems.getSubMenuIcon());
            holder.cardView_SubMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        listener.onItemClick(modelItems);
                    }
                }
            });
//        holder.cardView_SubMenu.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation_three));
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }


    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSubMenu;
        TextView tvSubMenu;
        CardView cardView_SubMenu;

        public MyRecyclerViewHolder(View view) {
            super(view);
            tvSubMenu = view.findViewById(R.id.tvSubMenu);
            imgSubMenu = view.findViewById(R.id.imgSubMenu);
            cardView_SubMenu = view.findViewById(R.id.cardView_SubMenu);
        }
    }

    public SubMenuAdapter(List<SubMenuModel> re_dataList, Activity act) {
        this.menuList = re_dataList;
        activity = act;
    }

    // Interface to perform events on Main-List item click
    public interface OnItemsClickListener{
        void onItemClick(SubMenuModel subMenuModel);
    }
    // Main-list item clickListener is initialized
// This will be used in MainActivity
    public void setWhenClickListener(OnItemsClickListener listener){
        this.listener = listener;
    }
}
