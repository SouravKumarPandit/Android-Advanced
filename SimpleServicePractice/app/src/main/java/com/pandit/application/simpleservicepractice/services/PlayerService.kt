package com.pandit.application.simpleservicepractice.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.pandit.application.simpleservicepractice.R

class PlayerService : Service() {
    private lateinit  var mPlayer: MediaPlayer
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        mPlayer = MediaPlayer.create(this, R.raw.sample_audio)
//        mPlayer.prepare();
        mPlayer.start()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        mPlayer.stop()
        mPlayer.release()
        super.onDestroy()
    }
}