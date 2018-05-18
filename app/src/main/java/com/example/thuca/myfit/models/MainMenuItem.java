package com.example.thuca.myfit.models;

import com.example.thuca.myfit.R;

import java.util.ArrayList;
import java.util.List;

public class MainMenuItem {
    private String id;
    private String name;
    private int icon;

    public MainMenuItem(String id, String name, int icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public static List<MainMenuItem> createMenuItems() {
        List<MainMenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MainMenuItem("search", "Tra cứu", R.mipmap.search));

        return menuItems;
    }
}
