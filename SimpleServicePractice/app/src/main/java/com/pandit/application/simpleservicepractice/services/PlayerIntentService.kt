package com.pandit.application.simpleservicepractice.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log

private const val ACTION_FOO = "com.pandit.application.servicespractice.service.action.FOO"
private const val ACTION_BAZ = "com.pandit.application.servicespractice.service.action.BAZ"

private const val EXTRA_PARAM1 = "com.pandit.application.servicespractice.service.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.pandit.application.servicespractice.service.extra.PARAM2"

class PlayerIntentService : IntentService("IntentService") {
    override fun onHandleIntent(intent: Intent?) {
//        it's like worker thread  which call demanding services and stop when its done
        Log.i(
            "IntentService",
            "-----------------------------------------------Intent Service has started ------------------------------------------------------------------\""
        )
        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1!!, param2!!)
            }
            ACTION_BAZ -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionBaz(param1!!, param2!!)
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String, param2: String) {

    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String, param2: String) {
        Log.i("PRINT_INTENT_ACTION :", "$param1-----------$param2")
    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, PlayerIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        @JvmStatic
        fun startActionBaz(context: Context, param1: String, param2: String) {
            val intent = Intent(context, PlayerIntentService::class.java).apply {
                action = ACTION_BAZ
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}