package com.pandit.application.fcmnotificationpractice.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.pandit.application.fcmnotificationpractice.models.Deal;
import com.pandit.application.fcmnotificationpractice.models.DealsDAO;
import com.pandit.application.fcmnotificationpractice.models.DealsDatabase;

import java.util.List;

public class Repository {

    private static DealsDatabase dealsDatabase;
    private DealsDAO dealsDAO;
    private static final Object LOCK = new Object();

    public synchronized static DealsDatabase getDealsDatabase(Context context){
        if(dealsDatabase == null) {
            synchronized (LOCK) {
                if (dealsDatabase == null) {
                    dealsDatabase = Room.databaseBuilder(context,
                            DealsDatabase.class, "deals.db").build();
                }
            }
        }
        return dealsDatabase;
    }
    public LiveData<List<Deal>> getDeals(Context context) {
        if (dealsDAO == null) {
            dealsDAO = Repository.getDealsDatabase(context).dealsDAO();
        }
        return dealsDAO.getDeals();
    }
}