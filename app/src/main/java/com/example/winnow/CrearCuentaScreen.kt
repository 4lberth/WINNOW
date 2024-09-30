package com.example.winnow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

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

