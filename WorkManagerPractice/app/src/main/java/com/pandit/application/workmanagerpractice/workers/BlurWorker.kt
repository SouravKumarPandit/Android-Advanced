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
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.FileNotFoundException

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext

        // Makes a notification when the work starts and slows down the work so that it's easier to
        // see each WorkRequest start, even on emulated devices
        makeStatusNotification("Blurring image", appContext)
        sleep()

        return try {
//            val outputData = createBlurredBitmap(appContext, inputData.getString(KEY_IMAGE_URI))
//            val outputData: Data = Data.EMPTY
            val outputData: Data = Data.Builder().build()


//            Result.success()
            /*pass if having data */
            Result.success(outputData)
        } catch (fileNotFoundException: FileNotFoundException) {
            throw RuntimeException("Failed to decode input stream", fileNotFoundException)
        } catch (throwable: Throwable) {
            // If there were errors, return FAILURE
            Result.failure()
        }
    }

}