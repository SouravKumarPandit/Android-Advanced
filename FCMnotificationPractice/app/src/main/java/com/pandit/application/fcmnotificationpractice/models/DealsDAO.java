package com.pandit.application.fcmnotificationpractice.models;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DealsDAO {
    @Query("SELECT * FROM deal")
    public LiveData<List<Deal>> getDeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertDeal(Deal deal);

    @Query("DELETE FROM deal WHERE expiry < :expiryIn")
    public void deleteAllCoupons(String expiryIn);
}