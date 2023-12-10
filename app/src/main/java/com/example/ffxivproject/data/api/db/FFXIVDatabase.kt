package com.example.ffxivproject.data.api.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [MountEntity::class, ArmourEntity::class], version = 1)
@TypeConverters(RecursosListTypeConverter::class)
abstract class FFXIVDatabase(): RoomDatabase() {

    abstract fun ffxivDao():FFXIVDao

    companion object {
        @Volatile
        private var INSTANCE: FFXIVDatabase? = null

        fun getInstance(context: Context): FFXIVDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): FFXIVDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                FFXIVDatabase::class.java,
                "ffxiv_db"
            ).build()
        }
    }
}