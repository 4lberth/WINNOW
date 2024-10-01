package com.example.winnow.data.db.tablas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "notificaciones",
    foreignKeys = [ForeignKey(
        entity = Usuarios::class,
        parentColumns = ["idUsu"],
        childColumns = ["idUsu"],
        onDelete = ForeignKey.CASCADE)
    ]
)
data class Notificaciones(
    @PrimaryKey(autoGenerate = true)
    val idNotificacion: Int = 0, // ID único de la notificación

    @ColumnInfo(name = "idUsu")
    val idUsu: Int, // ID del usuario que recibe la notificación (FK)

    @ColumnInfo(name = "mensaje")
    val mensaje: String, // Contenido de la notificación

    @ColumnInfo(name = "fecha")
    val fechaNotificacion: Date, // Fecha en la que se envió la notificación

    @ColumnInfo(name = "leido")
    val leido: Boolean = false // Si la notificación ha sido leída o no
)