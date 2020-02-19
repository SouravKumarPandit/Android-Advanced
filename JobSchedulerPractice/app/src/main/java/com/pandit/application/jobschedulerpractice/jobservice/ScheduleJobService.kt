package com.pandit.application.jobschedulerpractice.jobservice

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log


class ScheduleJobService : JobService() {
    override fun onStartJob(jobParameters: JobParameters): Boolean {

        val yourTask = YourTask()
        yourTask.doYourTask(this)

        Log.e("JOB_SCHEDULER",jobParameters.extras.getString("data","No Data"))

        return false
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        return false
    }
}