package com.pandit.application.workmanagerpractice.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class CleanupWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {

        // Makes a notification when the work starts and slows down the work so that
        // it's easier to see each WorkRequest start, even on emulated devices
        makeStatusNotification("Cleaning up old temporary files", applicationContext)
        sleep()

        return try {

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}