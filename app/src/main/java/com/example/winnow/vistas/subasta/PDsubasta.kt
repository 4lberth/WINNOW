package com.example.winnow.vistas.subasta

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.winnow.R
import com.example.winnow.vistas.PDsubasta

//Estructura de las PDSubastas
@Composable
fun PDSubastaList(
    pdSubastaList: List<PDsubasta>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        // Mostrar en filas de 2 columnas
        items(pdSubastaList.chunked(2)) { pdSubastaRow ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (pdSubasta in pdSubastaRow) {
                    PDSubastaItem(pdSubasta, Modifier.weight(1f), navController)
                }
                // Si la fila tiene solo un elemento, llenamos el espacio vacío
                if (pdSubastaRow.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

//Contendo de las PDSubasta
@Composable
fun PDSubastaItem(
    pdSubasta: PDsubasta,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Imagen cuadrada
        Image(
            painter = painterResource(id = R.drawable.sample_image), // Imagen de ejemplo
            contentDescription = "Imagen de ${pdSubasta.titulo}",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop
        )

        // Título, descripción y precio
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = pdSubasta.titulo,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp
        )
        Text(
            text = pdSubasta.descripcion,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = pdSubasta.precio,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Green
        )

        // Botón debajo de la información del producto
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                // Navegar a la pantalla de detalle de la subasta
                navController.navigate("detalle_subasta/${pdSubasta.titulo}/${pdSubasta.descripcion}/${pdSubasta.precio}")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Pujar")
        }
    }
}

