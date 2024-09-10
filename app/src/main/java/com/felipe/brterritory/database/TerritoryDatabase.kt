// TerritoryDatabase.kt
package com.felipe.brterritory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Territory::class, Leader::class], version = 2)
abstract class TerritoryDatabase : RoomDatabase() {
    abstract fun territoryDao(): TerritoryDao
    abstract fun leaderDao(): LeaderDao

    companion object {
        @Volatile
        private var INSTANCE: TerritoryDatabase? = null

        fun getInstance(context: Context): TerritoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TerritoryDatabase::class.java,
                    "territory_database"
                ).fallbackToDestructiveMigration() // Atualize o banco de dados para a nova versão
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
