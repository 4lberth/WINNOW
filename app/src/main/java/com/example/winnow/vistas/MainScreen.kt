package com.example.winnow.vistas

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.winnow.vistas.subasta.PDSubastaList
import com.example.winnow.R

@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    val isRegistered = sharedPreferences.getBoolean("isRegistered", false)

    if (isRegistered) {
        navController.navigate("profile_screen")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchBar(navController = navController)
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        floatingActionButton = {
            Spacer(modifier = Modifier.height(16.dp)) // Espacio adicional
            FloatingActionButton(
                onClick = { /* Acción del FAB */ },
                shape = RoundedCornerShape(50),
                containerColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp) // Espacio adicional
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        val pdSubastaList = listOf(
            PDsubasta("Producto 1", "Descripción del producto 1", "$100"),
            PDsubasta("Producto 2", "Descripción del producto 2", "$200"),
            PDsubasta("Producto 3", "Descripción del producto 3", "$150"),
            PDsubasta("Producto 4", "Descripción del producto 4", "$250")
        )

        PDSubastaList(pdSubastaList, navController, Modifier.padding(innerPadding))
    }
}

//Contenido de la barra de navegacion
@Composable
fun SearchBar(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    val userPhotoUrl = sharedPreferences.getString("userPhotoUrl", null) // Obtener la URL de la foto de perfil

    // Si el usuario está registrado, muestra su foto de perfil en la esquina superior derecha
    if (userPhotoUrl != null) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.sample_image), // Usa una imagen por defecto si no hay URL
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50.dp)) // Esquina redondeada para la foto
                    .clickable {
                        navController.navigate("profile_screen") // Navega al perfil del usuario
                    }
            )
        }
    } else {
        // Si no está registrado, muestra los botones de iniciar sesión y crear cuenta
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo de la aplicación a la izquierda
            Image(
                painter = painterResource(id = R.drawable.logowinnow),
                contentDescription = "Logo",
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Botón de iniciar sesión a la derecha
            Button(onClick = {
                navController.navigate("login_screen") // Navega a la pantalla de inicio de sesión
            }) {
                Text("Iniciar Sesión")
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Botón de crear Cuenta
            Button(onClick = { navController.navigate("crearCuenta") }) {
                Text("Crear Cuenta")
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary // Usar el color primario definido en el tema
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate("main_screen") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Categories") },
            label = { Text("Categorías") },
            selected = false,
            onClick = { /* Acción para categorías */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            label = { Text("Favoritos") },
            selected = false,
            onClick = { /* Acción para favoritos */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Perfil") },
            selected = false,
            onClick = { navController.navigate("profile_screen") }
        )
    }
}

