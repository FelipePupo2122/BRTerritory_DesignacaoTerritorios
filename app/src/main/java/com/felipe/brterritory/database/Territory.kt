package com.felipe.brterritory.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "territories")
data class Territory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val territoryId: String,
    val filePath: String
)
