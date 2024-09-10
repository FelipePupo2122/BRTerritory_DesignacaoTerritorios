package com.felipe.brterritory.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TerritoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(territory: Territory)

    @Query("SELECT * FROM territories")
    suspend fun getAllTerritories(): List<Territory>
}