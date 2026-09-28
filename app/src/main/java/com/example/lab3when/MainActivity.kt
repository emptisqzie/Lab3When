package com.example.lab3when

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab3when.ui.theme.Lab3WhenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3WhenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val input = remember { mutableStateOf("") }
    val result = remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(20.dp)
    ) {
        OutlinedTextField(
            value = input.value,
            onValueChange = { input.value = it },
            label = {
                Text("Введите символ")
            }
        )

        Button(
            onClick = {
                if (input.value.length != 1 || input.value[0] !in 'A'..'Z') {
                    result.value = "Ошибка: введите одну латинскую прописную букву"
                }
                else {
                    when (input.value[0]) {
                        'L', 'M', 'K', 'D' -> {
                            result.value = "Это согласные буквы"
                        }

                        else -> {
                            result.value = "Возможно, это гласные буквы"
                        }
                    }
                }
            },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text("Проверить")
        }

        Text(
            text = result.value,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Lab3WhenTheme {
        MainScreen()
    }
}