package com.pandit.application.simpleservicepractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pandit.application.simpleservicepractice.services.PlayerIntentService
import com.pandit.application.simpleservicepractice.services.PlayerService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPlay.setOnClickListener {
            val intent=Intent(this,PlayerService::class.java)
            startService(intent)

        }
        btnPause.setOnClickListener {  }
        btnStop.setOnClickListener {
            val intent=Intent(this,PlayerService::class.java)
            stopService(intent)
        }
        btnStartIntentService.setOnClickListener{
            startServeIntent()
        }
    }

    private  fun startServeIntent() {

//        val intent=Intent(this, PlayerIntentService::class.java)
//        startService(intent)
        PlayerIntentService.startActionBaz(this,"BAZ ACTION : param coming from here","another param is here")
    }
}
