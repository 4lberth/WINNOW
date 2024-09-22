package com.example.winnow.usuario.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.winnow.usuario.room.Usuarios

@Composable
fun UserProfileScreen(user: Usuarios, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // Información del usuario
        Text(text = "Nombre: ${user.nombresUsu}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Apellido: ${user.apellidosUsu}", fontSize = 16.sp)
        Text(text = "Email: ${user.emailUsu}", fontSize = 16.sp)
        Text(text = "Teléfono: ${user.telefonoUsu}", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Editar
        Button(onClick = { /* Navegar a la pantalla de edición */ }) {
            Text(text = "Editar Perfil")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Historial de actividades (opcional)
        Text(text = "Historial de Actividades", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        // Aquí podrías agregar una lista de subastas o actividades recientes
    }
}
