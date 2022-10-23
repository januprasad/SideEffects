package com.github.copalcocote

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

sealed class ScreenEvents {

    sealed class OutEventDataClass {

        data class CounterUp(val time: Long) : OutEventDataClass()

        data class CounterDown(val time: Long) : OutEventDataClass()
    }

    sealed class InEvent {
        object CounterUp : InEvent()
        object CounterDown : InEvent()
    }
}

class MainViewModel : ViewModel() {
    var currentValue = mutableStateOf(2000L)
    private val _sharedFlow: MutableSharedFlow<ScreenEvents.OutEventDataClass> = MutableSharedFlow()
    var sharedFlow = _sharedFlow.asSharedFlow()
    fun updateState(events: ScreenEvents.InEvent) {
        when (events) {
            ScreenEvents.InEvent.CounterDown -> {
                viewModelScope.launch {
                    currentValue.value -= 1000L
                    val event = ScreenEvents.OutEventDataClass.CounterDown(currentValue.value)
                    _sharedFlow.emit(event)
                }
            }

            ScreenEvents.InEvent.CounterUp -> {
                viewModelScope.launch {
                    currentValue.value += 1000L
                    val event = ScreenEvents.OutEventDataClass.CounterUp(currentValue.value)
                    _sharedFlow.emit(
                        event
                    )
                }
            }
        }
    }
}
