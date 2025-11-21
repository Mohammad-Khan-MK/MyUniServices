package com.example.myuniservices.data.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myuniservices.data.entities.ServiceEntity

@Dao
interface ServiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: ServiceEntity)

    @Query("SELECT * FROM services")
    suspend fun getAllServices(): List<ServiceEntity>

    @Query("DELETE FROM services")
    suspend fun deleteAll()
}
