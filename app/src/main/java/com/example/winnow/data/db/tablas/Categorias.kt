package com.example.winnow.data.db.tablas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias") //tabla categorias
data class Categorias(
    @PrimaryKey(autoGenerate = true)
    val idCat: Int = 0, // ID único generado por la base de datos

    @ColumnInfo(name = "nombres")
    val nombreCat: String
)