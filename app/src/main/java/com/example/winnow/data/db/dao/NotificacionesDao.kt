package com.example.winnow.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.winnow.data.db.tablas.Notificaciones

@Dao
interface NotificacionesDao {
    @Query("SELECT * FROM notificaciones")
    suspend fun getAll(): List<Notificaciones>

    @Insert
    suspend fun insert(notificacion: Notificaciones)

    @Delete
    suspend fun delete(notificacion: Notificaciones)
}