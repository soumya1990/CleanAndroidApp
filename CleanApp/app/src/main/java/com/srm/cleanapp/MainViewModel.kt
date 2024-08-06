package com.srm.cleanapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val adder: NumberAdder = NumberAdder()) : ViewModel() {

    var resultState: String by mutableStateOf("0")
        private set

    fun calculateSum(a: String, b: String) {
        viewModelScope.launch(Dispatchers.IO) {
            resultState = adder.add(a, b)
        }
    }
}