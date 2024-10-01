package com.example.winnow.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.winnow.data.db.tablas.Categorias

@Dao
interface CategoriasDao {
    @Query("SELECT * FROM categorias")
    suspend fun getAll(): List<Categorias>

    @Insert
    suspend fun insert(categoria: Categorias)

    @Delete
    suspend fun delete(categoria: Categorias)
}