package com.example.winnow.data.db.tablas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(//tabla subastas
    tableName = "subastas",
    foreignKeys = [ForeignKey(
        entity = Usuarios::class,
        parentColumns = ["idUsu"],
        childColumns = ["idUsu"],
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
        entity = Categorias::class,
        parentColumns = ["idCat"],
        childColumns = ["idCat"],
        onDelete = ForeignKey.SET_NULL)// Se pone en null si la categoría es eliminada
    ]
)
data class Subastas(
    @PrimaryKey(autoGenerate = true)
    val idSubasta: Int = 0, // ID único de la subasta

    @ColumnInfo(name = "idUsu")
    val idUsu: Int, // ID del usuario creador (FK)

    @ColumnInfo(name = "titulo")
    val tituloSubasta: String,

    @ColumnInfo(name = "descripcion")
    val descripcionSubasta: String,

    @ColumnInfo(name = "idCat")
    val idCat: Int, // ID de la categoría (FK), puede ser nulo

    @ColumnInfo(name = "precioInicial")
    val precioInicial: Double,

    @ColumnInfo(name = "compraInmediata")
    val compraInmediata: Double,

    @ColumnInfo(name = "fechaInicio")
    val fechaInicio: Date,

    @ColumnInfo(name = "fechaFin")
    val fechaFin: Date,

    @ColumnInfo(name = "estado")
    val estadoSubasta: String // Estado de la subasta (activa, finalizada)
)