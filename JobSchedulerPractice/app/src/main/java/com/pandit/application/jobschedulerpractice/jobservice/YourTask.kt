package com.pandit.application.jobschedulerpractice.jobservice

import android.content.Context
import java.util.concurrent.Executor

class YourTask {
    private val taskExecutor: TaskExecutor
    fun doYourTask(ctx: Context?) {
        /*do some network request and update database*/
        taskExecutor.execute(RoomUpdateTask( /*data on success of task ,*/ctx))
    }

    inner class TaskExecutor : Executor {
        override fun execute(runnable: Runnable) {
            val t = Thread(runnable)
            t.start()
        }
    }

    inner class RoomUpdateTask //            cpnList = cpnListIn;
        ( /* data here */
        //        private data;
        private val context: Context?
    ) : Runnable {

        override fun run() {
            updateDatabaseOrSomething( /*data here,*/context)
        }

    }

    private fun updateDatabaseOrSomething( /*data here,*/
        ctx: Context?
    ) { //        Log.d("ROOM", "local database update complete");
//        Log.d("ROOM", "number of local records " +
    }

    init {
        taskExecutor =
            TaskExecutor()
    }
}