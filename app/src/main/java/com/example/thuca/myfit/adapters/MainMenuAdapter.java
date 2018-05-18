package com.example.thuca.myfit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thuca.myfit.R;
import com.example.thuca.myfit.models.MainMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> implements Filterable {

    private List<MainMenuItem> menuItems, tempItems, suggestions;
    private Context context;

    public MainMenuAdapter(List<MainMenuItem> menuItems, Context context) {
        this.menuItems = menuItems;
        tempItems = new ArrayList<>(menuItems);
        suggestions = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View typeView = inflater.inflate(R.layout.main_menu_item, parent, false);

        // Return a new holder instance
        return new ViewHolder(typeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainMenuItem menuItem = menuItems.get(position);

        ImageView imgType = holder.imgType;
        imgType.setImageResource(menuItem.getIcon());
        TextView tvType = holder.tvType;
        tvType.setText(menuItem.getName());
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgType;
        TextView tvType;

        ViewHolder(View itemView) {
            super(itemView);
            imgType = itemView.findViewById(R.id.img_type);
            tvType = itemView.findViewById(R.id.tv_type);
        }
    }
}