package com.example.winnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.* // Importa las clases necesarias para el layout
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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









