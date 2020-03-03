package com.pandit.application.accountmanagerpractice.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
        val userId: String,
        val displayName: String
)
