package com.example.winnow.vistas.subasta

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.winnow.R

@Composable
fun DetalleSubasta(titulo: String, descripcion: String, precio: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Imagen del producto
            Image(
                painter = painterResource(id = R.drawable.sample_image), // Reemplazar con tu imagen
                contentDescription = "Imagen de $titulo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // Altura de la imagen
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Información recibida
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = descripcion, style = MaterialTheme.typography.bodySmall)
            Text(
                text = "Precio: $precio",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Green
            )

            // Botones de acción
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Acción de Subasta */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text(text = "Subasta", color = Color.Black)
                }

                Button(
                    onClick = { /* Acción de Compra Directa */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                ) {
                    Text(text = "C. Directa", color = Color.White)
                }
            }
        }
    }
}
