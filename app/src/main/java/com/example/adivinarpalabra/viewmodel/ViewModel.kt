package com.example.adivinarpalabra.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adivinarpalabra.model.Datos
import com.example.adivinarpalabra.model.Diccionario
import com.example.adivinarpalabra.model.Estados
import kotlin.random.Random
class ViewModel:ViewModel() {

    val random = Random

    private var _palabraJugadorLiveData = MutableLiveData<String>()
    val palabraJugadorLiveData: LiveData<String> get() = _palabraJugadorLiveData

    private val _sinonimoLiveData = MutableLiveData<String>()
    val sinonimoLiveData: LiveData<String> get() = _sinonimoLiveData

    private var _rondasLiveData = MutableLiveData<Int>()
    val rondasLiveData: LiveData<Int> get() = _rondasLiveData

    private var _aciertosLiveData = MutableLiveData<Int>()
    val aciertosLiveData: LiveData<Int> get() = _aciertosLiveData

    private var _fallosLiveData = MutableLiveData<Int>()
    val fallosLiveData: LiveData<Int> get() = _fallosLiveData

    val estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    init {
        _palabraJugadorLiveData.value = Datos.palabraJugador
        _sinonimoLiveData.value = Datos.sinonimo
        _rondasLiveData.value = Datos.ronda
        _aciertosLiveData.value = Datos.aciertos
        _fallosLiveData.value = Datos.fallos
    }


    fun setPalabraDir() {
        estadoLiveData.value = Estados.GENERANDO
        var numeroAleatorioDiccionario = random.nextInt(1, 21)
        var palabraDir = checkPalabra(numeroAleatorioDiccionario)
        var sinonimoDir = checkSinonimo(palabraDir)

        Log.d("Comprobando", palabraDir)
        Log.d("Comprobando", sinonimoDir)
        estadoLiveData.value = Estados.ADIVINANDO
    }

    fun addPalabraJugador(palabraJugador: String, palabraMaquina: String) {
        Log.d("palabraJ", getPalabraJugador())
        Log.d("Comprobando", palabraJugador)
        Log.d("Comprobando", palabraMaquina)
        winOrLose(palabraJugador, palabraMaquina)
    }

    private fun checkPalabra(id: Int): String {
        val palabra = Diccionario.entries.find { it.id == id }
        if (palabra != null) {
            setPalabraMaquina(palabra.nombre)
            return palabra.nombre
        }
        return ""
    }

    fun checkSinonimo(palabra: String): String {
        val sinoninoDir = Diccionario.entries.find { it.nombre == palabra }
        if (sinoninoDir != null) {
            setSinonimo(sinoninoDir.sinonimo)
            return sinoninoDir.sinonimo
        }
        return ""
    }

    fun winOrLose(palabraJugador: String, palabraMaquina: String) {
        if (palabraJugador == palabraMaquina) {
            Log.d("GanarOPerder", "Has ganado")
            setRondas()
            setAciertos()
            restartFallos()
            restartSinonimo()
            estadoLiveData.value = Estados.INICIO
        } else {
            setFallos()
            auxWinOrLose(getAciertos(), getFallos())
        }

    }

    private fun auxWinOrLose(aciertos: Int, fallos: Int) {
        if (aciertos == 0 && fallos == 3) {
            estadoLiveData.value = Estados.INICIO
            Log.d("GanarOPerder", "Has perdido")
            restartRondas()
            restartAciertos()
            restartFallos()
            restartSinonimo()
        } else if (aciertos == 1 && fallos == 2) {
            estadoLiveData.value = Estados.INICIO
            Log.d("GanarOPerder", "Has perdido")
            restartRondas()
            restartAciertos()
            restartFallos()
            restartSinonimo()
        } else if (aciertos > 1 && fallos == 1) {
            estadoLiveData.value = Estados.INICIO
            Log.d("GanarOPerder", "Has perdido")
            restartRondas()
            restartAciertos()
            restartFallos()
            restartSinonimo()
        }
    }

    fun setPalabraMaquina(palabraMaquina: String) {
        Datos.palabra = palabraMaquina
    }

    fun setSinonimo(sinonimoDir: String) {
        Datos.sinonimo = sinonimoDir
        _sinonimoLiveData.value = Datos.sinonimo
    }

    fun setPalabraJugador(texto: String) {
        Datos.palabraJugador = texto
        _palabraJugadorLiveData.value = Datos.palabraJugador
    }

    fun setRondas() {
        Datos.ronda += 1
        _rondasLiveData.value = Datos.ronda
    }

    fun setAciertos() {
        Datos.aciertos += 1
        _aciertosLiveData.value = Datos.aciertos
    }

    fun setFallos() {
        Datos.fallos += 1
        _fallosLiveData.value = Datos.fallos
    }

    fun getPalabraMaquina(): String {
        return Datos.palabra
    }

    fun getPalabraJugador(): String {
        return Datos.palabraJugador
    }

    fun getSinonimo(): String {
        return Datos.sinonimo
    }

    fun getRonda(): Int {
        return Datos.ronda
    }

    fun getAciertos(): Int {
        return Datos.aciertos
    }

    fun getFallos(): Int {
        return Datos.fallos
    }

    fun restartRondas() {
        Datos.ronda = 0
        _rondasLiveData.value = Datos.ronda
    }

    fun restartAciertos() {
        Datos.aciertos = 0
        _aciertosLiveData.value = Datos.aciertos
    }

    fun restartFallos() {
        Datos.fallos = 0
        _fallosLiveData.value = Datos.fallos
    }

    fun restartSinonimo() {
        Datos.sinonimo = ""
        _sinonimoLiveData.value = ""
    }
}



