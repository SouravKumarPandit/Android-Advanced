package com.pandit.application.fcmnotificationpractice.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pandit.application.fcmnotificationpractice.R;
import com.pandit.application.fcmnotificationpractice.ShoppingDealsActivity;

import java.util.Map;

public class DealsMessagingService extends FirebaseMessagingService {

    private static final String TAG = "DealsMessagingService";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "Received message " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data " + remoteMessage.getData());
            scheduleJob(remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification "
                    + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification());
        }
    }

    private void scheduleJob(Map<String, String> data) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }

        FirebaseJobDispatcher dispatcher =
                new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(DealsJobService.class)
                .setTag("deals-job")
                .setExtras(bundle)
                .build();
        dispatcher.schedule(myJob);
    }
    private void sendNotification(RemoteMessage.Notification notification) {
        Intent intent = new Intent(this, ShoppingDealsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, "fcm-channel")
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(notification.getTitle())
                        .setContentText(notification.getBody())
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(0, notificationBuilder.build());
    }
}