package com.example.listaappcarlosredondoumb

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

//Esta funcion muestra los detalles de la lista de compras seleccionada por el usuario
@Composable
fun DetalleListaScreen(lista: ListaCompra, volver: () -> Unit) {


    //las listas disponibles se organizan de forma vertical en columnas
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        //Titulo nombre de la lista
        Text(
            text = lista.nombre,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

        //usando un ciclo foreach se listan en tarjetas los articulos disponibles en la lista en columnas
        lista.articulos.forEach { articulo ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = articulo.nombre,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text("Cantidad: ${articulo.cantidad}")

                    Text("Precio: $${articulo.precio}")
                }
            }
        }

        //espacio para organizar
        Spacer(modifier = Modifier.height(16.dp))

        //Boton para volver
        Button(
            onClick = volver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver a mis listas")
        }
    }
}