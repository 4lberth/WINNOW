package com.example.winnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.winnow.ui.theme.WINNOWTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WINNOWTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchBar() // Barra de búsqueda con logo y botón
        }
    ) { innerPadding ->
        // Simulando lista de datos de la subasta (PDsubasta)
        val pdSubastaList = listOf(
            PDsubasta("Producto 1", "Descripción del producto 1", "$100"),
            PDsubasta("Producto 2", "Descripción del producto 2", "$200"),
            PDsubasta("Producto 3", "Descripción del producto 3", "$150"),
            PDsubasta("Producto 4", "Descripción del producto 4", "$250")
        )

        // Mostrar lista en dos columnas
        PDSubastaList(pdSubastaList, Modifier.padding(innerPadding))
    }
}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    // Diseño de la barra de búsqueda con logo y botón
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo de la aplicación a la izquierda
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Logo de ejemplo
            contentDescription = "Logo",
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Campo de búsqueda centrado
        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .background(Color.LightGray, MaterialTheme.shapes.medium)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Botón de iniciar sesión a la derecha
        Button(onClick = { /* Acción de iniciar sesión */ }) {
            Text("Iniciar Sesión")
        }
    }
}

// Definición de los datos de la subasta (PDsubasta)
data class PDsubasta(
    val titulo: String,
    val descripcion: String,
    val precio: String
)

@Composable
fun PDSubastaList(pdSubastaList: List<PDsubasta>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        // Mostrar en filas de 2 columnas
        items(pdSubastaList.chunked(2)) { pdSubastaRow ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                for (pdSubasta in pdSubastaRow) {
                    PDSubastaItem(pdSubasta, Modifier.weight(1f))
                }
                // Si la fila tiene solo un elemento, llenamos el espacio vacío
                if (pdSubastaRow.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun PDSubastaItem(pdSubasta: PDsubasta, modifier: Modifier = Modifier) {
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
        Text(text = pdSubasta.titulo, style = MaterialTheme.typography.titleMedium, fontSize = 18.sp)
        Text(text = pdSubasta.descripcion, style = MaterialTheme.typography.bodySmall)
        Text(text = pdSubasta.precio, style = MaterialTheme.typography.bodyLarge, color = Color.Green)

        // Botón debajo de la información del producto
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Acción al presionar el botón */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Pujar")
        }
    }
}
