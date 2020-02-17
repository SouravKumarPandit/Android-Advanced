package com.pandit.application.workmanagerpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.pandit.application.workmanagerpractice.workers.BlurWorker
import com.pandit.application.workmanagerpractice.workers.CleanupWorker
import com.pandit.application.workmanagerpractice.workers.SaveImageToFileWorker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.system.measureNanoTime

class MainActivity : AppCompatActivity() {

    private val workManager: WorkManager = WorkManager.getInstance(application)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btnStartWork.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            startWorker(1)
        }
        btnCancelWork.setOnClickListener{view->
            cancelWork()

        }
    }
    private fun cancelWork() {
        val cancelUniqueWork = workManager.cancelUniqueWork(WORK_NAME)
//        val result = cancelUniqueWork.result
//        result.isCancelled
    }


    private fun startWorker(blurLevel: Int) {

        // Add WorkRequest to Cleanup temporary images
        var continuation = workManager
            .beginUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CleanupWorker::class.java)
            )

        // Actually start the work
        continuation.enqueue()

    }
    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        return builder.build()
    }

}
