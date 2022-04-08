package com.example.adiblarappp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.adiblarappp.database.dao.AdibDao
import com.example.adiblarappp.database.entity.Adib


@Database(
    entities = [Adib::class], version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun adibDao(): AdibDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "my.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}
