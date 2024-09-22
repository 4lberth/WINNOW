package com.example.winnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.winnow.ui.theme.WINNOWTheme
import com.example.winnow.usuario.room.Usuarios
import com.example.winnow.usuario.view.UserProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Ejemplo de un usuario
        val exampleUser = Usuarios(
            dniUsu = "87654321",
            nombresUsu = "Carlos",
            apellidosUsu = "Gómez",
            emailUsu = "carlos.gomez@example.com",
            contrasenaUsu = "123456",
            telefonoUsu = "998877665"
        )

        setContent {
            WINNOWTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Pasar innerPadding a UserProfileScreen
                    UserProfileScreen(
                        user = exampleUser,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}