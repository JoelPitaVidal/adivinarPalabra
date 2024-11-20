package com.example.adivinarpalabra.model

object Datos {
    var palabra = ""
    var palabraJugador = ""
    var sinonimo = ""
    var ronda = 0
    var aciertos = 0
    var fallos = 0
}

enum class Diccionario(val id:Int, val nombre:String, val sinonimo:String){
    PALABRA1(id = 1, "amor", "afecto"),
    PALABRA2(id = 2, "felicidad", "gozo"),
    PALABRA3(id = 3, "tristeza", "desconsuelo"),
    PALABRA4(id = 4, "rapido", "agil"),
    PALABRA5(id = 5, "lento", "tardío"),
    PALABRA6(id = 6, "fuerte", "vigoroso"),
    PALABRA7(id = 7, "debil", "indefenso"),
    PALABRA8(id = 8, "hermoso", "atractivo"),
    PALABRA9(id = 9, "grande", "inmenso"),
    PALABRA10(id = 10, "pequeño", "reducido"),
    PALABRA11(id = 11, "inteligente", "sabio"),
    PALABRA12(id = 12, "torpe", "incapaz"),
    PALABRA13(id = 13, "frio", "gélido"),
    PALABRA14(id = 14, "caliente", "cálido"),
    PALABRA15(id = 15, "facil", "simple"),
    PALABRA16(id = 16, "dificil", "complicado"),
    PALABRA17(id = 17, "contento", "satisfecho"),
    PALABRA18(id = 18, "enojado", "irritado"),
    PALABRA19(id = 19, "oscuro", "tenebroso"),
    PALABRA20(id = 20, "claro", "brillante")

}