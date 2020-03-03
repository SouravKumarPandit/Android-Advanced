package com.pandit.application.fcmnotificationpractice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pandit.application.fcmnotificationpractice.adapter.DealsRecyclerViewAdapter;
import com.pandit.application.fcmnotificationpractice.database.Repository;

public class ShoppingDealsActivity extends AppCompatActivity {

    private static final String TAG = "ShoppingDealsActivity";
    private RecyclerView dealsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_deals_layout);

//        Toolbar tb = findViewById(R.id.toolbar);
//        setSupportActionBar(tb);
//        tb.setSubtitle("FCM");

        //on first run of the app, subscribe to FCM deals topic
        //to get latest deals from server as and when they are available
        subscribeToFcmDealsTopic();

        dealsRecyclerView = findViewById(R.id.deals_lst);

        LinearLayoutManager recyclerLayoutManager =
                new LinearLayoutManager(this.getApplicationContext());
        dealsRecyclerView.setLayoutManager(recyclerLayoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(dealsRecyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        dealsRecyclerView.addItemDecoration(dividerItemDecoration);

        Repository.getDealsDatabase(this).dealsDAO().getDeals()
                .observe(this, dealsList -> {
                    if (dealsList == null) {
                        return;
                    }
                    DealsRecyclerViewAdapter recyclerViewAdapter = new
                            DealsRecyclerViewAdapter(dealsList, ShoppingDealsActivity.this);
                    dealsRecyclerView.setAdapter(recyclerViewAdapter);
                });
    }
    //subscribes to firebase cloud messaging topic one time
    private void subscribeToFcmDealsTopic(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstRun", false)) {

            FirebaseMessaging.getInstance().subscribeToTopic("deals");

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstRun", true);
            editor.apply();
        }
    }
}