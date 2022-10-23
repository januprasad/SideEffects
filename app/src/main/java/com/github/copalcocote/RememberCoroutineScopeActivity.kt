package com.github.copalcocote

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.copalcocote.ui.theme.CopalcocoteTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RememberCoroutineScopeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CopalcocoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Timer()
                }
            }
        }
    }

    @Composable
    fun Timer() {
        val coroutineScope = rememberCoroutineScope()
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                Toast.makeText(
                    this@RememberCoroutineScopeActivity,
                    "The timer Started",
                    Toast.LENGTH_SHORT
                ).show()
                coroutineScope.launch {
                    try {
                        initTimer(2000) {
                            Toast.makeText(
                                this@RememberCoroutineScopeActivity,
                                "The timer ended",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@RememberCoroutineScopeActivity,
                            "The timer was cancelled: $e",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }) {
                Text("Start")
            }
        }
    }

    suspend fun initTimer(time: Long, Finish: () -> Unit) {
        delay(timeMillis = time)
        Finish()
    }
}
