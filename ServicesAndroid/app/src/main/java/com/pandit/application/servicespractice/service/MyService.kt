package com.pandit.application.servicespractice.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.pandit.application.servicespractice.IMyAidlInterface

class MyService : Service() {

   private var myAildImpl: MyAildImpl = MyAildImpl();

    private class MyAildImpl : IMyAidlInterface.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
        }

        override fun calculate(num1: Int, num2: Int): Int {
            return num1 + num2;

        }


    }

    override fun onBind(intent: Intent?): IBinder? {
        return myAildImpl
    }
}
