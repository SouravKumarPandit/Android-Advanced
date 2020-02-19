package com.pandit.application.workmanagerpractice

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.*
import com.pandit.application.workmanagerpractice.workers.AfterTaskDone
import com.pandit.application.workmanagerpractice.workers.BeforeTaskToDo
import com.pandit.application.workmanagerpractice.workers.TaskToDo
import com.pandit.application.workmanagerpractice.workers.makeStatusNotification
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


/**
 *  Use for sequences execution of task
 * */
class MainActivity : AppCompatActivity() {

    private val workManager: WorkManager = WorkManager.getInstance(application)
    private var outputWorkInfoItems: LiveData<List<WorkInfo>>? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        outputWorkInfoItems = workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)


        btnStartWork.setOnClickListener { _ ->
            startWorker()
            outputWorkInfoItems!!.observe(this, workInfosObserver())
        }
        btnCancleWork.setOnClickListener { _ ->
            cancelWork()

        }
    }

    private fun cancelWork() {
        workManager.cancelUniqueWork(WORK_NAME)
        makeStatusNotification("CANCELED $WORK_NAME JOBS", this)
    }


    private fun startWorker() {

//--------------------------------------------------------------------------------------------------------
        /* BEFORE DOING SOME WORK*/
        var continuation: WorkContinuation = workManager
            .beginUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(BeforeTaskToDo::class.java)
            )
//--------------------------------------------------------------------------------------------------------
        /* DOING SOME WORK*/
        val doingTask: OneTimeWorkRequest.Builder = OneTimeWorkRequest.Builder(TaskToDo::class.java)
        /*pass data to doing task  createInputDataForUri*/
        doingTask.setInputData(createInputDataForUri())

        continuation = continuation.then(doingTask.build())


//--------------------------------------------------------------------------------------------------------
        /* AFTER DOING SOME WORK*/

        val constraints: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val afterDoingTask = OneTimeWorkRequest.Builder(AfterTaskDone::class.java)
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()
        continuation = continuation.then(afterDoingTask)


//--------------------------------------------------------------------------------------------------------
        // Actually start the work
        val result = continuation.enqueue().result
//        result.addListener(Runnable {  })

    }

    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        builder.putString("data", "I am work data")
        return builder.build()
    }
    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            // Note that these next few lines grab a single WorkInfo if it exists
            // This code could be in a Transformation in the ViewModel; they are included here
            // so that the entire process of displaying a WorkInfo is in one location.

            // If there are no matching work info, do nothing
            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }

            // We only care about the one output status.
            // Every continuation has only one worker tagged TAG_OUTPUT
            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                showWorkFinished()

                // Normally this processing, which is not directly related to drawing views on
                // screen would be in the ViewModel. For simplicity we are keeping it here.
                val outputImageUri = workInfo.outputData.getString(DATA_KEY)

                // If there is an output file show "See File" button
                if (!outputImageUri.isNullOrEmpty()) {
                    setOutputUri(outputImageUri as String)
                    progressBar.visibility = View.GONE
                }
            } else {
                showWorkInProgress()
            }
        }
    }

    private fun showWorkInProgress() {
        progressBar.visibility = View.VISIBLE
        btnCancleWork.visibility = View.VISIBLE
    }

    /**
     * Shows and hides views for when the Activity is done processing an image
     */
    private fun showWorkFinished() {
        progressBar.visibility = View.GONE
        btnCancleWork.visibility = View.GONE
    }
    private fun setOutputUri(outputImageUri: String?) {
        val uriOrNull = uriOrNull(outputImageUri)
    }

    private fun uriOrNull(uriString: String?): Uri? {
        return if (!uriString.isNullOrEmpty()) {
            Uri.parse(uriString)
        } else {
            null
        }
    }
}
