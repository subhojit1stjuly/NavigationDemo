package com.reprototyper.navigationdemo.MenuFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reprototyper.navigationdemo.Adapter.MainMenuAdapter;
import com.reprototyper.navigationdemo.Adapter.SubMenuAdapter;
import com.reprototyper.navigationdemo.MenuModels.MenuModel;
import com.reprototyper.navigationdemo.MenuModels.SubMenuModel;
import com.reprototyper.navigationdemo.R;

import java.util.ArrayList;

public class navMenuFragment extends Fragment {

    private  ArrayList<MenuModel> menu;
    private ArrayList<SubMenuModel> subMenuModels;
    private String title;
    RecyclerView.LayoutManager mRecyclerViewLayoutManager;
    public boolean isSubMenu;
    Activity activity;


//    private OnItemsClickListener listener;

    public navMenuFragment() {
        // Required empty public constructor
    }
    public navMenuFragment(ArrayList<MenuModel> menu, Activity activity, boolean isSubMenu){
        this.menu = menu;
        this.activity = activity;
        this.isSubMenu = isSubMenu;
    }
    public navMenuFragment(ArrayList<SubMenuModel> subMenuModels, String title, Activity activity, boolean isSubMenu){
        this.subMenuModels = subMenuModels;
        this.title = title;
        this.activity=activity;
        this.isSubMenu = isSubMenu;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        if(isSubMenu){
            view = inflater.inflate(R.layout.layout_submenu, container, false);
            TextView tvMenuName = view.findViewById(R.id.tvMenuName);
            RecyclerView recyclerSubMenu = view.findViewById(R.id.recyclerSubMenu);
            Button btnBack = view.findViewById(R.id.btnBack);

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                        fm.popBackStack();
                    }
                }
            });
            tvMenuName.setText(title);
            recyclerSubMenu.setHasFixedSize(true);
            mRecyclerViewLayoutManager = new LinearLayoutManager(recyclerSubMenu.getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerSubMenu.setLayoutManager(mRecyclerViewLayoutManager);
            recyclerSubMenu.setItemAnimator(new DefaultItemAnimator());


            SubMenuAdapter adapter = new SubMenuAdapter(subMenuModels, getActivity());
            recyclerSubMenu.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            adapter.setWhenClickListener(new SubMenuAdapter.OnItemsClickListener() {
                @Override
                public void onItemClick(SubMenuModel subMenuModel) {
                    onMenuItemClick(subMenuModel.getId());
                }
            });
        }else{
            view= inflater.inflate(R.layout.layout_menu, container, false);
            RecyclerView recyclerMenu = view.findViewById(R.id.recyclerMenu);

            recyclerMenu.setHasFixedSize(true);
            mRecyclerViewLayoutManager = new LinearLayoutManager(recyclerMenu.getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerMenu.setLayoutManager(mRecyclerViewLayoutManager);
            recyclerMenu.setItemAnimator(new DefaultItemAnimator());


            MainMenuAdapter adapter = new MainMenuAdapter(menu, getActivity());
            recyclerMenu.setAdapter(adapter);
            adapter.notifyDataSetChanged();


            adapter.setWhenClickListener(new MainMenuAdapter.OnItemsClickListener() {
                @Override
                public void onItemClick(MenuModel menuModel) {
                    if(menuModel.getSubMenuModelList().size()>0){
                        Fragment selectedFragment = new navMenuFragment(menuModel.getSubMenuModelList(),menuModel.getMenuName(),activity,true);
                        FragmentTransaction transaction = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.scene_root, selectedFragment);
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }else{
                        onMenuItemClick(menuModel.getId());
                    }

                }
            });
        }
        return view;
    }
    public void onMenuItemClick(String MenuId){
        switch (MenuId){
            case "ic_menu":
                Toast.makeText(activity,"ic_menu",Toast.LENGTH_LONG).show();
                break;
            case "ic_sub01":
                Toast.makeText(activity,"ic_sub01",Toast.LENGTH_LONG).show();
                break;
        }
    }
/*    // Interface to perform events on Main-List item click
    public interface OnItemsClickListener{
        void onItemClick(String menuId);
    }
    // Main-list item clickListener is initialized
// This will be used in MainActivity
    public void setWhenClickListener(OnItemsClickListener listener){
        this.listener = listener;
    }*/

}