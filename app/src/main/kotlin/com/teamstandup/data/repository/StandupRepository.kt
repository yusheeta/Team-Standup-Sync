package com.teamstandup.data.repository

import com.teamstandup.data.remote.FirebaseService
import com.teamstandup.model.Standup

interface StandupRepository {
    suspend fun createStandup(teamId: String, standup: Standup)
    suspend fun fetchStandups(teamId: String): List<Standup>
}

class StandupRepositoryImpl(private val firebaseService: FirebaseService) : StandupRepository {
    override suspend fun createStandup(teamId: String, standup: Standup) {
        firebaseService.addStandup(teamId, standup)
    }

    override suspend fun fetchStandups(teamId: String): List<Standup> {
        return firebaseService.getStandups(teamId)
    }
}
