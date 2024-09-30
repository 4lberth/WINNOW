package com.example.winnow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchBar(navController = navController) // Barra de búsqueda con logo y botón
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
        PDSubastaList(pdSubastaList, navController, Modifier.padding(innerPadding))
    }
}



//Contenido de la barra de navegacion
@Composable
fun SearchBar(navController: NavHostController) {
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
            painter = painterResource(id = R.drawable.logowinnow), // Logo de ejemplo
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
        Button(onClick = {
            navController.navigate("login_screen") // Navega a la pantalla de inicio de sesión
        }) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.width(16.dp))

        //Botón de crear Cuenta
        Button(onClick = { navController.navigate("crearCuenta") }) {
            Text("Crear Cuenta")
        }
    }
}