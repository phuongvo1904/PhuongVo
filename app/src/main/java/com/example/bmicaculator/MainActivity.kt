package com.example.bmicaculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicaculator.ui.theme.BMICaculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICaculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    BMIScreen()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIScreen(modifier: Modifier = Modifier)
{
    var cao by remember { mutableStateOf("")}
    var nang by remember{ mutableStateOf("")}
    var result by remember { mutableStateOf("") }
    Scaffold(
        modifier = modifier.padding(5.dp),
        {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue, titleContentColor = Color.White),
                title = {
                    Text(
                        text = "BMI Calculator",
                        fontWeight = FontWeight.ExtraBold
                    )
                })
        }
    )
    {
        it ->
        Column(modifier = modifier
            .padding(it)
            .fillMaxSize())
        {
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, top = 20.dp),
                value = cao,
                onValueChange = {cao = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {Text(text = "Cm", color = Color.Gray)},
                label = {Text(text = "Chiều cao")}
            )
            TextField(modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
                value = nang,
                onValueChange = {nang = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {Text(text = "Kg", color = Color.Gray)},
                label = {Text(text = "Cân nặng")}
            )
            Box(modifier = modifier.padding(bottom = 20.dp))
            {
                Text(
                    text = "BMI: $result", fontWeight = FontWeight.ExtraBold,
                    color = Color.Red
                )
            }
            Button(
                onClick = {
                    try{
                        result = ""
                        var c = cao.toDouble()
                        var n = nang.toDouble()
                        var kq = n / Math.pow(c/100,2.0)
                        result += "${String.format("%.2f",kq)}."
                        result += if(kq < 18.5)
                        {
                            "Bạn gầy hơn so với chuẩn"
                        }else if(kq > 25){
                            "Bạn nặng hơn so với chuẩn"
                        }
                        else{
                            "Hoàn toàn bình thường"
                        }
                    } catch (e: Exception)
                    {
                        result = "Nhập dữ liệu sai"
                    }
                },
                modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Tính", fontWeight =  FontWeight.Bold)
            }
        }
    }
}



