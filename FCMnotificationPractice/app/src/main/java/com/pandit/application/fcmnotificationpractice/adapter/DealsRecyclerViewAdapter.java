package com.pandit.application.fcmnotificationpractice.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.pandit.application.fcmnotificationpractice.R;
import com.pandit.application.fcmnotificationpractice.models.Deal;

import java.util.List;

public class DealsRecyclerViewAdapter extends
        RecyclerView.Adapter<DealsRecyclerViewAdapter.ViewHolder> {

    private List<Deal> dealsList;
    private Context context;

    public DealsRecyclerViewAdapter(List<Deal> list, Context ctx) {
        dealsList = list;
        context = ctx;
    }
    @Override
    public int getItemCount() {
        return dealsList.size();
    }

    @Override
    public DealsRecyclerViewAdapter.ViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deal_item_layout, parent, false);

        DealsRecyclerViewAdapter.ViewHolder viewHolder =
                new DealsRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DealsRecyclerViewAdapter.ViewHolder holder, int position) {
        final int itemPos = position;
        final Deal deal = dealsList.get(position);
        holder.store.setText(deal.getStoreNAME());
        holder.deal.setText(deal.getDeal());
        holder.desc.setText(deal.getDealDesc());
        holder.expiry.setText(deal.getExpiry());
        holder.code.setText(deal.getCode());

        holder.shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Deals Recyclerview", "Shop ....");
                Toast.makeText(context,
                        "Visit "+deal.getStoreNAME(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView store;
        public TextView deal;
        public TextView desc;
        public TextView expiry;
        public TextView code;
        public Button shop;

        public ViewHolder(View view) {
            super(view);
            store = (TextView) view.findViewById(R.id.store_name);
            deal = (TextView) view.findViewById(R.id.deal_name);
            desc = (TextView) view.findViewById(R.id.deal_description);
            expiry = (TextView) view.findViewById(R.id.deal_expiry);
            code = (TextView) view.findViewById(R.id.deal_code);
            shop = view.findViewById(R.id.shop_b);
        }
    }
}