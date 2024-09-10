// LeaderDAO.kt
package com.felipe.brterritory.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LeaderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(leader: Leader)

    @Query("SELECT * FROM leaders")
    suspend fun getAllLeaders(): List<Leader>
}