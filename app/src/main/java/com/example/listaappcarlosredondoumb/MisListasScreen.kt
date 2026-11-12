package com.example.listaappcarlosredondoumb

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


//esta funcion muestra las listas guardadas y permite eliminarlas si el usuario lo solicita
@Composable
fun MisListasScreen(
    listas: List<ListaCompra>,
    volverAlMenu: () -> Unit,
    abrirDetalle: (ListaCompra) -> Unit,
    eliminarLista: (ListaCompra) -> Unit,
    )
{

    //organizamos las listas creadas usando columnas
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    )
    //titulo de la pantalla
        {
        Text(
            text = "Mis listas",
            style = MaterialTheme.typography.headlineMedium
        )

        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

    //Condicional que cambia el contenido dependiendo de si existen listas creadas o no
        if (listas.isEmpty()) {
            Text("Todavía no has creado ninguna lista.")

        //estructura para mostrar las listas en tarjetas y organizadas en una columna
        } else {
            listas.forEach { lista ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    onClick = {
                        abrirDetalle(lista)
                    }
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        //Organizacion por fila del titulo de la lista y el boton de eliminar lista
                        Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = lista.nombre,
                            style = MaterialTheme.typography.titleMedium
                        )

                        //Boton de eliminar lista
                        Button(
                            onClick = {
                                eliminarLista(lista)
                            }
                        ) {
                            Text("Eliminar")
                        }
                    }}

                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

            //boton para volver al menu principal
        Button(
            onClick = volverAlMenu,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al menú")
        }
    }
}