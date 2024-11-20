package com.example.adivinarpalabra.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.adivinarpalabra.R
import com.example.adivinarpalabra.viewmodel.ViewModel

@Composable
fun MyApp(viewModel: ViewModel) {
    val text by viewModel.palabraJugadorLiveData.observeAsState(viewModel.getPalabraJugador())
    val textSinonimo by viewModel.sinonimoLiveData.observeAsState(viewModel.getSinonimo())

    val ronda by viewModel.rondasLiveData.observeAsState(viewModel.getRonda())
    val acierto by viewModel.aciertosLiveData.observeAsState(viewModel.getAciertos())
    val fallo by viewModel.fallosLiveData.observeAsState(viewModel.getFallos())

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val backgroundImage = painterResource(id = R.drawable.fondoadivinapalabra)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
        Column {
            Row {
                ShowRondas(ronda)
            }
            Row {
                ShowSinonimo(textSinonimo)
            }
            Row {
                TextNombreEscribir(remember { mutableStateOf(text) }, viewModel)
            }
            Row {
                ButtonEnter(viewModel, text, viewModel.getPalabraMaquina())
            }
            Row {
                ShowAciertos(acierto)
                ShowFallos(fallo)
            }
            Row {
                ButtonStart(viewModel)
            }
        }
    }
}

@Composable
fun ShowRondas(rondas:Int){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 80.dp, start = 140.dp)
    ) {

        Text(text = "Ronda: $rondas" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black)

    }

}

@Composable
fun ShowSinonimo(sinonimo:String){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 30.dp, start = 140.dp)
    ) {

        Text(text = "Sinonimo: $sinonimo" ,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black)

    }

}

@Composable
fun TextNombreEscribir(text: MutableState<String>, viewModel: ViewModel) {

    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.textoActivo) }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.textoActivo
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 20.dp, start = 45.dp)
    ) {
    }
    TextField(
        enabled = _activo,
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
            viewModel.setPalabraJugador(text.value)
            Log.d("ComprobarNombre", viewModel.getPalabraJugador())
        },
        placeholder = { Text("palabra aqui...") },
        modifier = Modifier
            .padding(top = 150.dp, start = 16.dp)
    )
}

@Composable
fun ButtonEnter(viewModel: ViewModel, palabraJugador:String, palabraMaquina:String){

    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.enterActivo) }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.enterActivo
    }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 20.dp, start = 115.dp)) {
        Button(
            enabled = _activo,
            onClick = {

                viewModel.escribirPalabra(palabraJugador, palabraMaquina)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
            ),
            modifier = Modifier
                .padding(top = 40.dp)
                .size(width = 150.dp, height = 60.dp)
                .clip(CircleShape)
        ){
            Text(
                text = "Corregir",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun ShowAciertos(aciertos:Int){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 80.dp, start = 45.dp)

    ) {

        Text(text = "Aciertos: $aciertos" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black)

    }

}

@Composable
fun ShowFallos(fallos:Int){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 80.dp, start = 70.dp)
    ) {

        Text(text = "Fallos: $fallos" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black)

    }

}

@Composable
fun ButtonStart(viewModel: ViewModel){

    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.startActivo) }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.startActivo
    }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 5.dp, start = 110.dp)) {
        Button(
            enabled = _activo,
            onClick = {
                viewModel.añadirPalabraAleatoria()
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
            ),
            modifier = Modifier
                .padding(top = 80.dp)
                .size(width = 150.dp, height = 60.dp)
        ){
            Text(
                text = "Start",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}