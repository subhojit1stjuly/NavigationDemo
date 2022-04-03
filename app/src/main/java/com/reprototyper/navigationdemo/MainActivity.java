package com.reprototyper.navigationdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.reprototyper.navigationdemo.MenuFragment.navMenuFragment;
import com.reprototyper.navigationdemo.MenuModels.MenuModel;
import com.reprototyper.navigationdemo.MenuModels.SubMenuModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;


    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("IdeaNavigation");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        initializeMenuItems();

        selectedFragment = new navMenuFragment(NavigationIdea.menu,this,false);
        getSupportFragmentManager().beginTransaction().replace(R.id.scene_root, selectedFragment).commit();
    }

    public void initializeMenuItems(){

        ArrayList<SubMenuModel> subMenuModel1 = new ArrayList<>();
        ArrayList<SubMenuModel> subMenuModel2 = new ArrayList<>();

        subMenuModel1.add(new SubMenuModel("SubMenuOne",R.drawable.ic_dashbord,"ic_sub01"));
        subMenuModel1.add(new SubMenuModel("SubMenuTwo",R.drawable.ic_dashbord,"ic_sub02"));
        subMenuModel1.add(new SubMenuModel("SubMenuThree",R.drawable.ic_dashbord,"ic_sub03"));
        subMenuModel1.add(new SubMenuModel("SubMenuFour",R.drawable.ic_dashbord,"ic_sub04"));
        subMenuModel1.add(new SubMenuModel("SubMenuFive",R.drawable.ic_dashbord,"ic_sub05"));

        NavigationIdea.menu.clear();

        NavigationIdea.menu.add(new MenuModel("MenuOne", subMenuModel1,R.drawable.ic_dashbord,""));
        NavigationIdea.menu.add(new MenuModel("MenuTwo", subMenuModel2,R.drawable.ic_dashbord,"ic_menu"));
    }
}