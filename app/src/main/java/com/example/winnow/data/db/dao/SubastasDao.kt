package com.example.winnow.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.winnow.data.db.tablas.Subastas

@Dao
interface SubastasDao {
    @Query("SELECT * FROM subastas")
    suspend fun getAll(): List<Subastas>

    @Insert
    suspend fun insert(subasta: Subastas)

    @Delete
    suspend fun delete(subasta: Subastas)
}