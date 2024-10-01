package com.example.winnow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.winnow.data.db.dao.CategoriasDao
import com.example.winnow.data.db.dao.NotificacionesDao
import com.example.winnow.data.db.dao.PagosDao
import com.example.winnow.data.db.dao.SubastasDao
import com.example.winnow.data.db.tablas.Usuarios
import com.example.winnow.data.db.dao.UsuariosDao
import com.example.winnow.data.db.tablas.Categorias
import com.example.winnow.data.db.tablas.Notificaciones
import com.example.winnow.data.db.tablas.Pagos
import com.example.winnow.data.db.tablas.Subastas
import com.example.winnow.utils.Converters

@Database(
    entities = [
        Usuarios::class,
        Categorias::class,
        Subastas::class,
        Pagos::class,
        Notificaciones::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun usuariosDao(): UsuariosDao
    abstract fun categoriasDao(): CategoriasDao
    abstract fun subastasDao(): SubastasDao
    abstract fun pagosDao(): PagosDao
    abstract fun notificacionesDao(): NotificacionesDao
}