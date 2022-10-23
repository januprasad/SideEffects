package com.github.copalcocote

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.copalcocote.ui.theme.CopalcocoteTheme
import kotlinx.coroutines.delay

class LaunchedEffectMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CopalcocoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }

    @Composable
    fun MainApp() {
        /**
         * Please rotate the screen for understanding the Launch Effect, the time will be called cancelled.
         */
//        TimerAppNormal()
        TimerAppViewModel()
    }

    @Composable
    private
    fun TimerAppViewModel(mainViewModel: MainViewModel = viewModel()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {
            var timerDuration by remember {
                mutableStateOf(2000L)
            }

            LaunchedEffect(key1 = true) {
                mainViewModel.sharedFlow.collect { event ->
                    when (event) {
                        is ScreenEvents.OutEventDataClass.CounterDown -> {
                            println(event.time)
                            timerDuration = event.time
                        }

                        is ScreenEvents.OutEventDataClass.CounterUp -> {
                            println(event.time)
                            timerDuration = event.time
                        }
                    }
                }
            }
            TimerAppNormal(timerDuration)
            Button(onClick = {
                mainViewModel.updateState(ScreenEvents.InEvent.CounterDown)
            }) {
                Text(text = "-1 Seconds")
            }
            Text(text = "Seconds $timerDuration")
            Button(onClick = {
                mainViewModel.updateState(ScreenEvents.InEvent.CounterUp)
            }) {
                Text(text = "+1 Seconds")
            }
        }
    }

    @Composable
    fun TimerAppNormal(timerDuration: Long = 3000L) {
        LaunchedEffect(key1 = timerDuration) {
            try {
                startTimer(timerDuration) {
                    println("Timer ended")
                }
            } catch (e: Exception) {
                println("Timer cancelled")
            }
        }
    }

    private suspend fun startTimer(time: Long, onTimeEnd: () -> Unit) {
        println("Timer started")
        delay(time)
        onTimeEnd()
    }
}
