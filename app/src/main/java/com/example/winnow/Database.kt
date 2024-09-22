package com.example.winnow

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.winnow.usuario.room.Usuarios
import com.example.winnow.usuario.room.UsuariosDao

@Database(
    entities = [Usuarios::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao
}