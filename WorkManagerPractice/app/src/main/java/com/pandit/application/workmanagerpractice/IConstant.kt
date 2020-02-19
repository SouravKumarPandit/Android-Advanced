@file:JvmName("IConstants")
package com.pandit.application.workmanagerpractice
@JvmField val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
    "Verbose WorkManager Notifications"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
    "Shows notifications whenever work starts"
@JvmField val NOTIFICATION_TITLE: CharSequence = "WorkRequest Starting"
const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
const val NOTIFICATION_ID = 1

// The name of the image manipulation work
const val WORK_NAME = "Name of work"

// Other keys
const val DATA_KEY = "data-work"
const val TAG_OUTPUT = "OUTPUT"

const val DELAY_TIME_MILLIS: Long = 3000
