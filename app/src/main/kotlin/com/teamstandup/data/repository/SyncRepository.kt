package com.teamstandup.data.repository

import com.teamstandup.data.local.AppDatabase
import com.teamstandup.data.local.StandupEntity
import com.teamstandup.data.remote.FirebaseService
import com.teamstandup.model.Mood
import com.teamstandup.model.Standup
import com.teamstandup.model.SyncStatus
import java.util.UUID

interface SyncRepository {
    suspend fun queueStandup(standup: Standup, teamId: String)
    suspend fun syncPending(teamId: String)
}

class SyncRepositoryImpl(
    private val db: AppDatabase,
    private val firebaseService: FirebaseService
) : SyncRepository {

    override suspend fun queueStandup(standup: Standup, teamId: String) {
        db.standupDao().insert(standup.toEntity(teamId))
    }

    override suspend fun syncPending(teamId: String) {
        db.standupDao().getPending().forEach { entity ->
            try {
                val standup = entity.toModel()
                firebaseService.addStandup(teamId, standup)
                db.standupDao().updateSyncStatus(entity.id, SyncStatus.SYNCED.name)
            } catch (e: Exception) {
                db.standupDao().updateSyncStatus(entity.id, SyncStatus.FAILED.name)
            }
        }
    }
}

private fun Standup.toEntity(teamId: String) = StandupEntity(
    id = id.ifEmpty { UUID.randomUUID().toString() },
    userId = userId,
    teamId = teamId,
    text = text,
    mood = mood.name,
    blockers = blockers.joinToString("||"),
    voiceUrl = voiceUrl,
    timestamp = timestamp,
    syncStatus = SyncStatus.LOCAL.name
)

private fun StandupEntity.toModel() = Standup(
    id = id,
    userId = userId,
    text = text,
    mood = Mood.valueOf(mood),
    blockers = if (blockers.isBlank()) emptyList() else blockers.split("||"),
    voiceUrl = voiceUrl,
    timestamp = timestamp,
    syncStatus = SyncStatus.valueOf(syncStatus)
)
