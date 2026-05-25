package com.teamstandup.util

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ReminderWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            // Send local standup reminder notification
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}
