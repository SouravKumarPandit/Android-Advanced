package com.pandit.application.fcmnotificationpractice.fcm;


import android.os.Bundle;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.pandit.application.fcmnotificationpractice.database.Repository;
import com.pandit.application.fcmnotificationpractice.models.Deal;
import com.pandit.application.fcmnotificationpractice.models.DealsDAO;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DealsJobService extends JobService {

    private static final String TAG = "DealsJobService";

    private final Executor executor = Executors.newFixedThreadPool(2);
    private DealsDAO dealsDAO = Repository.getDealsDatabase(this).dealsDAO();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "updating ROOM database with latest deals");

        addDealsDataToSQLiteDatabase(jobParameters.getExtras());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    //add data to sqlite database using room
    private void addDealsDataToSQLiteDatabase(Bundle bundle) {

        final Deal dealObj = getDealObjectFromBundle(bundle);
        executor.execute(new Runnable() {
            @Override
            public void run() {
              long rec =  dealsDAO.insertDeal(dealObj);
                Log.d(TAG, "added record to db "+rec);
            }
        });

    }
    private Deal getDealObjectFromBundle(Bundle bundle){
        Deal deal = new Deal();
        deal.setStoreNAME(bundle.getString("storeNAME"));
        deal.setDeal(bundle.getString("deal"));
        deal.setDealDesc(bundle.getString("dealDesc"));
        deal.setExpiry(bundle.getString("expiry"));
        deal.setCode(bundle.getString("code"));

        return deal;
    }
}