/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pandit.application.workmanagerpractice.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.pandit.application.workmanagerpractice.DATA_KEY

/**
 * Saves the image to a permanent file
 */
class AfterTaskDone(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        // Makes a notification when the work starts and slows down the work so that
        // it's easier to see each WorkRequest start, even on emulated devices
        makeStatusNotification("WORK DONE PROGRESSES SAVED", applicationContext)
        sleep()

        val resolver = applicationContext.contentResolver

        return try {

            val resourceUri = inputData.getString(DATA_KEY)

            if (!resourceUri.isNullOrEmpty()) {
                val output = workDataOf(DATA_KEY to resourceUri)
                Result.success(output)
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
