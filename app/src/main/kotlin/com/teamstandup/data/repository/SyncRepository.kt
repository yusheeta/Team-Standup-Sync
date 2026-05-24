package com.teamstandup.data.repository

import com.teamstandup.model.Standup

interface SyncRepository {
    suspend fun queueStandup(standup: Standup)
    suspend fun syncPending(teamId: String)
}
