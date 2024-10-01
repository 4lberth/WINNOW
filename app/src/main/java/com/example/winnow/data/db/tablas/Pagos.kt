package com.example.winnow.data.db.tablas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "pagos",
    foreignKeys = [ForeignKey(
        entity = Usuarios::class,
        parentColumns = ["idUsu"],
        childColumns = ["idUsu"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = Subastas::class,
            parentColumns = ["idSubasta"],
            childColumns = ["idSubasta"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class Pagos(
    @PrimaryKey(autoGenerate = true)
    val idPago: Int = 0, // ID único del pago

    @ColumnInfo(name = "idSubasta")
    val idSubasta: Int, // ID de la subasta (FK)

    @ColumnInfo(name = "idUsu")
    val idUsu: Int, // ID del usuario que pagó (FK)

    @ColumnInfo(name = "monto")
    val montoPago: Double, // Monto del pago

    @ColumnInfo(name = "fecha")
    val fechaPago: Date, // Fecha del pago

    @ColumnInfo(name = "metodoPago")
    val metodoPago: String // Método de pago utilizado
)
