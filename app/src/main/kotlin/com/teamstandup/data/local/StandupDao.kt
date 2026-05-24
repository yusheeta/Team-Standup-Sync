package com.teamstandup.data.local

import com.teamstandup.model.Standup

interface StandupDao {
    suspend fun insert(standup: Standup)
    suspend fun pending(): List<Standup>
}
