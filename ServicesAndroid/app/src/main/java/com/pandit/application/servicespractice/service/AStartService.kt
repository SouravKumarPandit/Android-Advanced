package com.pandit.application.servicespractice.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.os.Looper
import android.widget.Toast

class AStartService : Service() {
    //    var mediaPlayer: MediaPlayer? = null
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

//        mediaPlayer=MediaPlayer.create(this,null)

//        to stop service call this else it will run continues
//        stopSelf();

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
//        mediaPlayer?.stop();
//        mediaPlayer?.release()
        super.onDestroy()
    }
}