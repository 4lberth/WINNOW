package com.example.winnow.vistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.winnow.CrearCuentaScreen
import com.example.winnow.DetalleSubasta
import com.example.winnow.LoginScreen
import com.example.winnow.MainScreen
import com.example.winnow.ui.theme.WINNOWTheme

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









