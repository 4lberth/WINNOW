package com.example.winnow.vistas.usuario

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.winnow.R
import com.example.winnow.data.db.tablas.Usuarios
import coil.compose.rememberAsyncImagePainter
import android.content.Context

@Composable
fun UserProfileScreen(user: Usuarios, navController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current // Obtener el contexto de la aplicación

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Imagen de perfil
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (user.fotoUsu != null) {
                Image(
                    painter = rememberAsyncImagePainter(user.fotoUsu),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Foto de perfil predeterminada",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Información del usuario
        Text(
            text = user.nombresUsu,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = user.emailUsu,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Editar Perfil
        Button(
            onClick = { /* Navegar a la pantalla de edición */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Editar Perfil")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de Subastas Recientes
        Text(
            text = "Subastas Recientes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Aquí puedes cargar subastas recientes
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            SubastaItem(titulo = "Reloj de Bolsillo Antiguo", ultimaOferta = "$120")
            SubastaItem(titulo = "Cámara Vintage", ultimaOferta = "$210")
            SubastaItem(titulo = "Pintura de Paisaje", ultimaOferta = "$350")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para ir al MainScreen
        Button(
            onClick = {
                // Navegar a la pantalla principal
                navController.navigate("main_screen")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Ir al MainScreen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de cerrar sesión
        Button(
            onClick = {
                // Eliminar el estado de sesión en SharedPreferences
                val sharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear() // Limpiar todas las preferencias compartidas
                editor.apply()

                // Navegar a la pantalla principal sin cuenta iniciada
                navController.navigate("main_screen") {
                    // Para evitar que el usuario regrese a esta pantalla usando el botón de retroceso
                    popUpTo("main_screen") { inclusive = true }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Cerrar sesión")
        }
    }
}

@Composable
fun SubastaItem(titulo: String, ultimaOferta: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        // Puedes agregar aquí una miniatura del ítem subastado
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Imagen de subasta",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = titulo, fontWeight = FontWeight.Bold)
            Text(text = "Última oferta: $ultimaOferta", fontSize = 14.sp)
        }
    }
}