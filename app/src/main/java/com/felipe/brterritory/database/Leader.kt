// Leader.kt
package com.felipe.brterritory.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaders")
data class Leader(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)


