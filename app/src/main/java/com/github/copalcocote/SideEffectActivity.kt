package com.github.copalcocote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.copalcocote.ui.theme.CopalcocoteTheme
import java.util.UUID

class SideEffectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CopalcocoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

var count = 0

@Composable
fun Greeting(name: String) {
    SideEffect {
        count += 1
    }

    var someState by remember {
        mutableStateOf("Android")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextWrapper(someState)
        Button(onClick = {
            someState = UUID.randomUUID().toString()
        }) {
            Text(text = "Text Changer")
        }
    }
}

@Composable
fun TextWrapper(text: String) {
    Text(text = "text $text!")
    Text(text = "Recompostion counter $count!")
}
