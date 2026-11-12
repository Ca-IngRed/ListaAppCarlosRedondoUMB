package com.example.listaappcarlosredondoumb



//Esta clase define la estructura de los datos que se usan


//estructura de los articulos
data class Articulo(
    val nombre: String,
    val cantidad: Int,
    val precio: Double
)

//estructura de la lista de compra
data class ListaCompra(
    val id: Int,
    val nombre: String,
    val articulos: List<Articulo>
)