package com.ucne.registropersonas

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.ucne.registropersonas.ui.theme.RegistroPersonasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroPersonasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Personas()
                }
            }
        }
    }
}

@Composable
fun Personas() {
    val campos = listOf(
        "Nombre" to remember { mutableStateOf("") },
        "Telefono" to remember { mutableStateOf("") },
        "Celular" to remember { mutableStateOf("") },
        "Email" to remember { mutableStateOf("") },
        "Fecha de nacimiento" to remember { mutableStateOf("") }
    )

    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro de personas",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 16.dp),
            )

            campos.forEach { (nombre, estado) ->
                OutlinedTextField(
                    value = estado.value,
                    onValueChange = { estado.value = it },
                    label = { Text(text = nombre) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 0.dp)
                )
            }

            val ocupaciones = listOf("Programador", "Ingeniero", "Médico", "Arquitecto", "Abogado")
            var ocupacionSeleccionada by remember { mutableStateOf("") }
            var expandido by remember { mutableStateOf(false) }

            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = ocupacionSeleccionada,
                    onValueChange = { ocupacionSeleccionada = it },
                    readOnly = true,
                    enabled = false,
                    label = { Text(text = "Ocupación") },
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    },
                    modifier = Modifier
                        .clickable {
                            expandido = true
                            Log.e("tag", "expandido")
                        }
                        .fillMaxWidth()
                )

                DropdownMenu(
                    expanded = expandido,
                    onDismissRequest = { expandido = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ocupaciones.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                expandido = false
                                ocupacionSeleccionada = item
                            })
                    }
                }
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 16.dp)
                    .height(50.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Guardar")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Guardar", color = Color.White)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    RegistroPersonasTheme {
        Personas()
    }
}