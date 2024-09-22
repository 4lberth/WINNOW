package com.example.winnow.usuario.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuariosDao {
    @Query("SELECT * FROM usuarios")
    suspend fun getAll(): List<Usuarios>

    @Insert
    suspend fun insert(usuario: Usuarios)

    @Delete
    suspend fun delete(usuario: Usuarios)
}