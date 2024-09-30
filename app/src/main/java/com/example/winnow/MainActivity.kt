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


// Definición de los datos de la subasta (PDsubasta)
data class PDsubasta(
    val titulo: String,
    val descripcion: String,
    val precio: String
)





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





