package com.example.winnow.vistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.winnow.data.db.tablas.Usuarios
import com.example.winnow.vistas.usuario.CrearCuentaScreen
import com.example.winnow.vistas.subasta.DetalleSubasta
import com.example.winnow.vistas.usuario.LoginScreen
import com.example.winnow.ui.theme.WINNOWTheme
import com.example.winnow.vistas.usuario.UserProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WINNOWTheme {
                val navController = rememberNavController()
                // Definimos el NavHost que controlará las rutas
                NavHost(navController = navController, startDestination = "main_screen") {
                    composable("main_screen") {
                        MainScreen(navController = navController)
                    }
                    composable("login_screen") {
                        LoginScreen(navController = navController)
                    }
                    composable("crearCuenta") {
                        CrearCuentaScreen(navController = navController)
                    }
                    // Agregar la pantalla de detalle de subasta
                    composable(
                        "detalle_subasta/{titulo}/{descripcion}/{precio}",
                        arguments = listOf(
                            navArgument("titulo") { type = NavType.StringType },
                            navArgument("descripcion") { type = NavType.StringType },
                            navArgument("precio") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val titulo = backStackEntry.arguments?.getString("titulo") ?: ""
                        val descripcion = backStackEntry.arguments?.getString("descripcion") ?: ""
                        val precio = backStackEntry.arguments?.getString("precio") ?: ""
                        DetalleSubasta(titulo, descripcion, precio)
                    }
                    // Agregar la pantalla de perfil del usuario
                    composable("profile_screen") {
                        // Simular un usuario para mostrar el perfil
                        val user = Usuarios(
                            dniUsu = "12345678",
                            nombresUsu = "Juan",
                            apellidosUsu = "Pérez",
                            emailUsu = "juan.perez@example.com",
                            contrasenaUsu = "password",
                            telefonoUsu = "123456789",
                            fotoUsu = null // Puedes asignar una URL de la foto si existe
                        )
                        UserProfileScreen(user = user, navController = navController) // Asegúrate de pasar navController aquí
                    }

                }
            }
        }
    }
}





// Definición de los datos de la subasta (PDsubasta)
data class PDsubasta(
    val titulo: String,
    val descripcion: String,
    val precio: String
)









