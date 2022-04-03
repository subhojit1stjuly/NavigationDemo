package com.reprototyper.navigationdemo.MenuModels;

public class SubMenuModel {
    String SubMenuName,id;
    int SubMenuIcon;

    public SubMenuModel(String subMenuName, int subMenuIcon, String id) {
        SubMenuName = subMenuName;
        this.id = id;
        SubMenuIcon = subMenuIcon;
    }

    public String getSubMenuName() {
        return SubMenuName;
    }

    public void setSubMenuName(String subMenuName) {
        SubMenuName = subMenuName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSubMenuIcon() {
        return SubMenuIcon;
    }

    public void setSubMenuIcon(int subMenuIcon) {
        SubMenuIcon = subMenuIcon;
    }
}
