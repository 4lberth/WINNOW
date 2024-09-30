package com.example.winnow.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.winnow.data.db.tablas.Usuarios

@Dao
interface UsuariosDao {
    @Query("SELECT * FROM usuarios")
    suspend fun getAll(): List<Usuarios>

    @Insert
    suspend fun insert(usuario: Usuarios)

    @Delete
    suspend fun delete(usuario: Usuarios)
}