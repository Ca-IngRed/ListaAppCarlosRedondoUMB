package com.example.listaappcarlosredondoumb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import com.example.listaappcarlosredondoumb.ui.theme.ListaAppCarlosRedondoUMBTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaAppCarlosRedondoUMBTheme {


                //variable para indicar si se debe mostrar la pantalla de formulario
                var mostrarFormulario by remember {
                    mutableStateOf(false)
                }

                //variable para almacenar las compras del usuario
                var listas by remember {
                    mutableStateOf(listOf<ListaCompra>())
                }

                //variable para indicar si se debe mostrar la pantalla de listas
                var mostrarMisListas by remember {
                    mutableStateOf(false)
                }

                //almacena la lista de compras sobre la que se va a ver el detalle
                var listaSeleccionada by remember {
                    mutableStateOf<ListaCompra?>(null)
                }


                // Si existe una lista seleccionada se muestra en pantalla sus detalles
                if (listaSeleccionada != null) {

                    //llama clase para ver detalle de lista
                    DetalleListaScreen(
                        lista = listaSeleccionada!!,
                        volver = {
                            listaSeleccionada = null
                        }
                    )

                //si el usuario lo desea creamos una lista con formulario
                } else if (mostrarFormulario) {

                    //lama clase crear lista
                    CrearListaScreen(
                        volverAlMenu = {
                            mostrarFormulario = false
                        },

                        //a cada lista se le asigna un identificador
                        agregarLista = { nuevaLista ->
                            val listaGuardada = nuevaLista.copy(
                                id = listas.size + 1
                            )
                            listas = listas + listaGuardada
                        }
                    )


                //se muestran las listas guardadas si se desea consultar se pueden ver detalles y eliminar
                } else if (mostrarMisListas) {

                    //llama clase para ver la lista de listas guardadas
                    MisListasScreen(
                        listas = listas,
                        volverAlMenu = {
                            mostrarMisListas = false
                        },
                        abrirDetalle = { lista ->
                            listaSeleccionada = lista
                        },
                        eliminarLista = { lista ->
                            listas = listas.filterNot {
                                it.id == lista.id
                            }
                        },
                    )
                //se muestra por defecto la pantalla inicial si no hay nada activo
                } else {

                    //hace referencia funcion de pantalla de inicio
                    InicioScreen(
                        abrirFormulario = {
                            mostrarFormulario = true
                        },
                        abrirMisListas = {
                            mostrarMisListas = true
                        }
                    )
                }
            }
        }
    }
}


// Pantalla principal
@Composable
fun InicioScreen(abrirFormulario: () -> Unit, abrirMisListas: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        //Encabezado
        Text(
            text = "Bienvenido",
            fontSize = 28.sp
        )

        Text(
            text = "Organiza tus próximas compras."
        )


        // Tarjeta crear lista
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = { abrirFormulario() }
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Crear lista",
                    fontSize = 20.sp
                )

                Text(
                    text = "Crea una nueva lista de compras."
                )
            }
        }

        // Tarjeta de mis listas
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = { abrirMisListas() }
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Mis listas",
                    fontSize = 20.sp
                )

                Text(
                    text = "Consulta y administra tus listas de compras."
                )
            }
        }
    }
}