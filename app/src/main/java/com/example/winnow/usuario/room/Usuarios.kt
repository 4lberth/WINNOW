package com.example.winnow.usuario.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios") //tabla usuarios
data class Usuarios(
    @PrimaryKey(autoGenerate = true)
    val idUsu: Int = 0, // ID único generado por la base de datos

    @ColumnInfo(name = "dni")
    val dniUsu: String, // DNI del usuario

    @ColumnInfo(name = "nombres")
    val nombresUsu: String,

    @ColumnInfo(name = "apellidos")
    val apellidosUsu: String,

    @ColumnInfo(name = "email")
    val emailUsu: String,

    @ColumnInfo(name = "contrasena")
    val contrasenaUsu: String,

    @ColumnInfo(name = "telefono")
    val telefonoUsu: String
)

