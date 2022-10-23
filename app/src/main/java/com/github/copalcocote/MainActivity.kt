package com.github.copalcocote

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.github.copalcocote.ui.theme.CopalcocoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CopalcocoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val c = LocalContext.current
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = {
            c.startActivity(Intent(c, LaunchedEffectMainActivity::class.java))
        }) {
            Text(text = "LaunchedEffectMainActivity")
        }
        Button(onClick = {
            c.startActivity(Intent(c, RememberCoroutineScopeActivity::class.java))
        }) {
            Text(text = "RememberCoroutineScopeActivity")
        }
        Button(onClick = {
            c.startActivity(Intent(c, SideEffectActivity::class.java))
        }) {
            Text(text = "SideEffectActivity")
        }
    }
}
