package com.example.myuniservices.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myuniservices.data.entities.ServiceEntity
import com.example.myuniservices.data.dao.ServiceDao


@Database(
    entities = [ServiceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serviceDao(): ServiceDao

}