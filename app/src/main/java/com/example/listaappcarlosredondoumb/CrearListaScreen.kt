
package com.example.listaappcarlosredondoumb

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
//funcion crea una lista de articulos
fun CrearListaScreen(volverAlMenu: () -> Unit, agregarLista: (ListaCompra) -> Unit) {


    //variable guarda el nombre de la lista
    var nombreLista by remember {
        mutableStateOf("")
    }

    //variable guarda el nombre del articulo
    var nombreArticulo by remember {
        mutableStateOf("")
    }

    //variable guarda cantidad de articulos
    var cantidadArticulo by remember {
        mutableStateOf("")
    }

    //variable guarda el precio del articulo
    var precioArticulo by remember {
        mutableStateOf("")
    }

    //variable que contiene todos los articulos ingresados a una lista
    var articulos by remember {
        mutableStateOf(listOf<Articulo>())
    }


    //Interfaz usando una Columna de objetos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        //Titulo
        Text(
            text = "Crear lista",
            style = MaterialTheme.typography.headlineMedium
        )

        //Boton de regresar
        Button(
            onClick = { volverAlMenu() }
        ) {
            Text("Volver al menú")
        }

        //Nombre de la lista
        OutlinedTextField(
            value = nombreLista,
            onValueChange = { nombreLista = it },
            label = { Text("Nombre de la lista") },
            modifier = Modifier.fillMaxWidth()
        )

        //divisor
        HorizontalDivider()

        Text(
            text = "Artículos",
            style = MaterialTheme.typography.titleLarge
        )

        //Nombre del artículo
        OutlinedTextField(
            value = nombreArticulo,
            onValueChange = { nombreArticulo = it },
            label = { Text("Nombre del artículo") },
            modifier = Modifier.fillMaxWidth()
        )

        //Cantidad
        OutlinedTextField(
            value = cantidadArticulo,
            onValueChange = { cantidadArticulo = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )

        //Precio
        OutlinedTextField(
            value = precioArticulo,
            onValueChange = { precioArticulo = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        //botón para agregar artículo
        Button(
            onClick = {

                if (
                    nombreArticulo.isNotBlank() &&
                    (cantidadArticulo.toIntOrNull() ?: 0) > 0 &&
                    (precioArticulo.toDoubleOrNull() ?: -1.0) >= 0
                ) {

                    val nuevoArticulo = Articulo(
                        nombre = nombreArticulo,
                        cantidad = cantidadArticulo.toIntOrNull()!!,
                        precio = precioArticulo.toDoubleOrNull()!!
                    )

                    articulos = articulos + nuevoArticulo

                    // Limpiamos los campos
                    nombreArticulo = ""
                    cantidadArticulo = ""
                    precioArticulo = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar artículo")
        }

        //divisor
        HorizontalDivider()

        // Espacio que muestra artículos agregados en una tarjeta
        Text(
            text = "Artículos agregados: ${articulos.size}",
            style = MaterialTheme.typography.titleMedium
        )

        articulos.forEachIndexed { index, articulo ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text("${index + 1}. ${articulo.nombre}")
                    Text("Cantidad: ${articulo.cantidad}")
                    Text("Precio: ${articulo.precio}")
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para guardar la lista completa
        Button(
            onClick = {

                val nuevaLista = ListaCompra(
                    id = 0,
                    nombre = nombreLista,
                    articulos = articulos
                )

                agregarLista(nuevaLista)

                volverAlMenu()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = nombreLista.isNotBlank() && articulos.isNotEmpty()
        ) {
            Text("Agregar lista")
        }
    }
}
