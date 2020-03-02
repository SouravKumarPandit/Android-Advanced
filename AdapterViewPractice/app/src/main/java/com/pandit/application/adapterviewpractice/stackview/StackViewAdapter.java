package com.pandit.application.adapterviewpractice.stackview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.pandit.application.adapterviewpractice.R;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StackViewAdapter extends ArrayAdapter {

    private List<String> storesList;
    private Context context;
    private int itemLayout;

    public StackViewAdapter(List<String> stores,int resource, Context ctx) {
        super(ctx, resource, stores);
        storesList = stores;
        context = ctx;
        itemLayout = resource;
    }

    @Override
    public int getCount() {
        return storesList.size();
    }
    @Override
    public String getItem(int position) {
        return storesList.get(position);
    }

    @NotNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(itemLayout, parent, false);
        }

        String store = storesList.get(position);

        TextView storeName = (TextView) view.findViewById(R.id.store_name);
        ImageView storeImg = (ImageView) view.findViewById(R.id.store_image);


        storeName.setText(store);
        int resId = context.getResources().getIdentifier(store,
              "drawable", context.getPackageName());
        storeImg.setImageResource(R.mipmap.ic_launcher_round);

        return view;
    }
}