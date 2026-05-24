package com.teamstandup.data.repository

import com.teamstandup.model.Standup

interface StandupRepository {
    suspend fun createStandup(teamId: String, standup: Standup)
    suspend fun fetchStandups(teamId: String): List<Standup>
}
