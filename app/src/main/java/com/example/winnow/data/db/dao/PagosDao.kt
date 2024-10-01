package com.example.winnow.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.winnow.data.db.tablas.Pagos

@Dao
interface PagosDao {
    @Query("SELECT * FROM pagos")
    suspend fun getAll(): List<Pagos>

    @Insert
    suspend fun insert(pago: Pagos)

    @Delete
    suspend fun delete(pago: Pagos)
}