package com.example.winnow.data.db.tablas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

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
    val telefonoUsu: String,

    @ColumnInfo(name = "foto_perfil")
    val fotoUsu: String?, //para permitir que el campo sea opcional (puede ser null si no se ha asignado una foto de perfil).

    @ColumnInfo(name = "fecha_registro")
    val fechaRegistroUsu: Date = Date() // Fecha de registro (por defecto, la actual)

)

