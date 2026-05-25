package com.teamstandup.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StandupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(standup: StandupEntity)

    @Query("SELECT * FROM standups WHERE syncStatus = 'LOCAL'")
    suspend fun getPending(): List<StandupEntity>

    @Query("SELECT * FROM standups WHERE teamId = :teamId ORDER BY timestamp DESC")
    fun getTeamStandups(teamId: String): Flow<List<StandupEntity>>

    @Query("UPDATE standups SET syncStatus = :status WHERE id = :id")
    suspend fun updateSyncStatus(id: String, status: String)
}
