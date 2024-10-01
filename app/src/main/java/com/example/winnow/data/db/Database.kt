package com.example.winnow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.winnow.data.db.dao.CategoriasDao
import com.example.winnow.data.db.dao.SubastasDao
import com.example.winnow.data.db.tablas.Usuarios
import com.example.winnow.data.db.dao.UsuariosDao

@Database(
    entities = [Usuarios::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao
    abstract fun categoriasDao(): CategoriasDao
    abstract fun subastasDao(): SubastasDao
}