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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.winnow.ui.theme.WINNOWTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Establecer contendo para la actividad
        //Resolver problema de "variable no esta siendo usada"
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
        PDSubastaList(pdSubastaList, Modifier.padding(innerPadding))
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


//Estructura de las PDSubastas
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


//Contendo de las PDSubasta
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


//Inicio de Sesion
@Composable
fun LoginScreen(navController: NavHostController) {
    // Diseño de la pantalla de inicio de sesión
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pantalla de Inicio de Sesión", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Acción al iniciar sesión */ }) {
            Text("Iniciar Sesión")
        }

        // Botón para volver
        TextButton(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}

// Crear Cuenta
@Composable
fun CrearCuentaScreen(navController: NavHostController) {
    var dni by remember { mutableStateOf("") }
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    // Mensaje de error para mostrar en la validación
    var errorMessage by remember { mutableStateOf("") }

    // Función para validar y crear cuenta
    fun crearCuenta() {
        if (dni.length !in 8..10) {
            errorMessage = "El DNI debe tener entre 8 y 10 dígitos"
        } else if (nombres.length > 100) {
            errorMessage = "El nombre no puede superar los 100 caracteres"
        } else if (apellidos.length > 100) {
            errorMessage = "El apellido no puede superar los 100 caracteres"
        } else if (email.length > 100) {
            errorMessage = "El correo no puede superar los 100 caracteres"
        } else if (contrasena.length < 8) {
            errorMessage = "La contraseña debe tener al menos 8 caracteres"
        } else if (telefono.length != 9) {
            errorMessage = "El teléfono debe tener 9 dígitos"
        } else {
            // Lógica para crear cuenta
            errorMessage = ""  // Limpiar error
            // Aquí iría el código para registrar el usuario en la base de datos
            navController.popBackStack() // Regresar a la pantalla anterior después de crear cuenta
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear Nueva Cuenta", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada de DNI
        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI") },
            isError = dni.length !in 8..10,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de entrada de nombres
        OutlinedTextField(
            value = nombres,
            onValueChange = { nombres = it },
            label = { Text("Nombres") },
            maxLines = 1,
            isError = nombres.length > 100,
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de entrada de apellidos
        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            maxLines = 1,
            isError = apellidos.length > 100,
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de entrada de email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            maxLines = 1,
            isError = email.length > 100,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de entrada de contraseña
        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            isError = contrasena.length < 8,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de entrada de teléfono
        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            isError = telefono.length != 9,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Mostrar mensaje de error si hay
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de crear cuenta
        Button(onClick = { crearCuenta() }, modifier = Modifier.fillMaxWidth()) {
            Text("Crear Cuenta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para volver
        TextButton(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}

