package com.reprototyper.navigationdemo.MenuModels;

import java.util.ArrayList;

public class MenuModel {
    String menuName,id;
    ArrayList<SubMenuModel> subMenuModelList;
    int menuIcon;

    public MenuModel(String menuName, ArrayList<SubMenuModel> subMenuModelList, int menuIcon, String id) {
        this.menuName = menuName;
        this.id = id;
        this.subMenuModelList = subMenuModelList;
        this.menuIcon = menuIcon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<SubMenuModel> getSubMenuModelList() {
        return subMenuModelList;
    }

    public void setSubMenuModelList(ArrayList<SubMenuModel> subMenuModelList) {
        this.subMenuModelList = subMenuModelList;
    }

    public int getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }
}
