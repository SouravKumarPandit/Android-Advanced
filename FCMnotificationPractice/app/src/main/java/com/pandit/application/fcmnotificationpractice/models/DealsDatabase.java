package com.pandit.application.fcmnotificationpractice.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Deal.class}, version = 1)
public abstract class DealsDatabase extends RoomDatabase {
    public abstract DealsDAO dealsDAO();
}