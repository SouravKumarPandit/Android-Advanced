package com.pandit.application.aidlservicepractice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pandit.application.aidlservicepractice.server.ICalculateServer
import kotlinx.android.synthetic.client.client_layout.*
import kotlin.random.Random


class HomeActivity : AppCompatActivity() {

    private lateinit var iCalculateServer: ICalculateServer
    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iCalculateServer = ICalculateServer.Stub.asInterface(service)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_layout)

        btnBindService.setOnClickListener {
            /*this will the action defined for server side app don't forget package id .
             not the package name as suffix if using android flavour*/
            val intent = Intent("com.pandit.application.aidlservicepractice.server.GET_SUM")

//            bindService(
//                convertImplicitIntentToExplicitIntent(intent, this),
//                serviceConnection,
//                Context.BIND_AUTO_CREATE
//            )


            intent.setPackage("com.pandit.application.aidlservicepractice.server")
            bindService(
                intent,
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )
        }
        val function: (View) -> Unit = {
            val randomNum1 = Random.nextInt(from = 0, until = 1000)
            val randomNum2 = Random.nextInt(from = 0, until = 1000)
            tvNumberOne.text = randomNum1.toString()
            tvNumberTwo.text = randomNum2.toString()

            val num1 = tvNumberOne.text.toString().toInt()
            val num2 = tvNumberTwo.text.toString().toInt()

            val doAddition = iCalculateServer.doAddition(num1, num2)

            tvResult.text = doAddition.toString()


        }
        btnUnbindService.setOnClickListener {
        }
        btnCalculateSum.setOnClickListener(function)

    }

    private fun convertImplicitIntentToExplicitIntent(
        implicitIntent: Intent,
        context: Context
    ): Intent? {
        val pm = context.packageManager
        val resolveInfoList =
            pm.queryIntentServices(implicitIntent, 0)
        if (resolveInfoList.size != 1) {
            return null
        }
        val serviceInfo = resolveInfoList[0]
        val component =
            ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name)
        val explicitIntent = Intent(implicitIntent)
        explicitIntent.component = component
        return explicitIntent
    }
}