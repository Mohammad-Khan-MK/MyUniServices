package com.example.myuniservices.data.entities



import androidx.room.Entity
import androidx.room.PrimaryKey

// This represents one row in the "services" table
@Entity(tableName = "services")
data class ServiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,          // Room will generate this ID
    val name: String,         // e.g. "Library"
    val category: String,     // e.g. "Academic", "Support"
    val description: String   // e.g. "Main campus library opening hours..."
)
