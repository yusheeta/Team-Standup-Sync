package com.teamstandup.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [StandupEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun standupDao(): StandupDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "teamstandup.db"
                ).build().also { INSTANCE = it }
            }
    }
}

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> =
        if (value.isBlank()) emptyList() else value.split("||")

    @TypeConverter
    fun toString(list: List<String>): String = list.joinToString("||")
}
