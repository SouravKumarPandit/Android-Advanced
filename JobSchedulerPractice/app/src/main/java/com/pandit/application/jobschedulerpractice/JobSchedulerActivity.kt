package com.pandit.application.jobschedulerpractice

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.pandit.application.jobschedulerpractice.jobservice.ScheduleJobService


class JobSchedulerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun scheduleJob() {

//        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
//        if (!preferences.getBoolean("firstRunComplete", false)) { //schedule the job only once.
            doSomeUpdate()
            //update shared preference
//            val editor = preferences.edit()
//            editor.putBoolean("firstRunComplete", true)
//            editor.apply()
//        }
    }

    private fun doSomeUpdate() {
        val jobScheduler = applicationContext
            .getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(
            this,
            ScheduleJobService::class.java
        )


        val bundle = PersistableBundle()
        bundle.putString("data", "I am scheduler data")


//        val jobInfo = JobInfo.Builder(1, componentName).setExtras(bundle)
//            .setPeriodic(86400000).setRequiredNetworkType(
//                JobInfo.NETWORK_TYPE_UNMETERED
//            )
//            .setPersisted(true).build()
//        jobScheduler.schedule(jobInfo)

        jobScheduler.schedule(
            JobInfo.Builder(
                1,
                componentName
            )
                .setExtras(bundle)
                .setMinimumLatency(1)
                .setOverrideDeadline(1)
                .build()
        )


    }

    fun doSchedulingTask(view : View) {
        scheduleJob()

    }
}