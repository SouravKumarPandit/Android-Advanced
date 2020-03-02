package com.pandit.application.aidlservicepractice.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.pandit.application.aidlservicepractice.server.ICalculateServer

class CalculatorService : Service() {
    private val implBinder = ImplBinder()
    override fun onBind(intent: Intent): IBinder {
        return implBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    class ImplBinder : ICalculateServer.Stub() {
        override fun doAddition(numberOne: Int, numberTwo: Int): Int {
            Log.i("AIDL_SERVICE", "getting sum from here")
            return numberOne + numberTwo
        }

    }
}
