    package com.example.winnow.vistas.usuario

    import android.content.Context
    import android.widget.Toast
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.text.KeyboardOptions
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.ArrowBack
    import androidx.compose.material.icons.filled.Person
    import androidx.compose.material.icons.filled.Email
    import androidx.compose.material.icons.filled.Lock
    import androidx.compose.material.icons.filled.Phone
    import androidx.compose.material3.Button
    import androidx.compose.material3.ButtonDefaults
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Text
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.input.KeyboardType
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.NavController
    import androidx.navigation.compose.rememberNavController
    import com.example.winnow.R
    import com.example.winnow.data.db.tablas.Usuarios
    import com.example.winnow.ui.theme.WINNOWTheme
    import java.util.Date

    @Composable
    fun CrearCuentaScreen(navController: NavController) {
        val context = LocalContext.current

        // Estados para cada campo
        var dni by remember { mutableStateOf("") }
        var nombres by remember { mutableStateOf("") }
        var apellidos by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var contrasena by remember { mutableStateOf("") }
        var telefono by remember { mutableStateOf("") }
        var fotoUrl by remember { mutableStateOf("") } // Campo opcional para la URL de la foto de perfil

        // Función al hacer clic en "Crear Cuenta"
        fun onCrearCuentaClick() {
            if (dni.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || email.isEmpty() || contrasena.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val nuevoUsuario = Usuarios(
                    dniUsu = dni,
                    nombresUsu = nombres,
                    apellidosUsu = apellidos,
                    emailUsu = email,
                    contrasenaUsu = contrasena,
                    telefonoUsu = telefono,
                    fotoUsu = if (fotoUrl.isNotEmpty()) fotoUrl else null,
                    fechaRegistroUsu = Date()
                )

                // Guardar el estado en SharedPreferences (para saber si está registrado)
                val sharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("isRegistered", true) // Marca que el usuario está registrado
                editor.putString("userPhotoUrl", fotoUrl) // Guardamos la URL de la foto si existe
                editor.apply()

                Toast.makeText(context, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                navController.navigate("main_screen") // Redirige al MainScreen después de crear la cuenta
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)), // Fondo gris claro
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f) // Controla el ancho de la columna
                    .clip(RoundedCornerShape(16.dp)) // Esquinas redondeadas
                    .background(Color.White) // Fondo blanco para el formulario
                    .padding(24.dp), // Margen interior
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Barra superior con el botón de retroceso
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) { // Acción para retroceder
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retroceder"
                        )
                    }

                    Text(
                        text = "Crear Cuenta",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6200EA),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(42.dp))
                }

                // Imagen decorativa en la parte superior
                Image(
                    painter = painterResource(id = R.drawable.logowinnow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(50.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Campos de entrada con íconos
                CustomOutlinedTextField(
                    value = dni,
                    onValueChange = { dni = it },
                    label = "DNI",
                    icon = Icons.Default.Person,
                    keyboardType = KeyboardType.Number
                )

                CustomOutlinedTextField(
                    value = nombres,
                    onValueChange = { nombres = it },
                    label = "Nombres",
                    icon = Icons.Default.Person
                )

                CustomOutlinedTextField(
                    value = apellidos,
                    onValueChange = { apellidos = it },
                    label = "Apellidos",
                    icon = Icons.Default.Person
                )

                CustomOutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email
                )

                CustomOutlinedTextField(
                    value = contrasena,
                    onValueChange = { contrasena = it },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    keyboardType = KeyboardType.Password
                )

                CustomOutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = "Teléfono",
                    icon = Icons.Default.Phone,
                    keyboardType = KeyboardType.Phone
                )

                CustomOutlinedTextField(
                    value = fotoUrl,
                    onValueChange = { fotoUrl = it },
                    label = "Foto de Perfil (URL opcional)",
                    icon = Icons.Default.Person
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para crear cuenta
                Button(
                    onClick = { onCrearCuentaClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF6200EA)) // Usar colors en lugar de backgroundColor
                ) {
                    Text(text = "Crear Cuenta", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }

    @Composable
    fun CustomOutlinedTextField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String,
        icon: androidx.compose.ui.graphics.vector.ImageVector,
        keyboardType: KeyboardType = KeyboardType.Text
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun CrearCuentaPreview() {
        WINNOWTheme {
            CrearCuentaScreen(navController = rememberNavController())
        }
    }
